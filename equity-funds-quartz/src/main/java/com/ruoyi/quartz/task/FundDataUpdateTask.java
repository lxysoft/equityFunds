package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.fund.data.FundData;
import com.ruoyi.fund.data.enums.FundStatus;
import com.ruoyi.fund.data.enums.FundType;
import com.ruoyi.fund.data.response.DetailData;
import com.ruoyi.fund.data.response.FundDetailResponse;
import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.domain.FundNetWorth;
import com.ruoyi.fund.service.IFundHoldService;
import com.ruoyi.fund.service.IFundInfoService;
import com.ruoyi.fund.service.IFundNetWorthService;
import com.ruoyi.fund.vo.FundInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 每日基金数据更新
 * @author xiaoyang.li
 * @date 2019/12/4 16:23
 */
@Component("fundDataUpdateTask")
public class FundDataUpdateTask {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IFundInfoService fundInfoService;
    @Autowired
    private IFundNetWorthService fundNetWorthService;
    @Autowired
    private IFundHoldService fundHoldService;

    /**
     * 更新基金最近数据
     */
    public void updateData(){
        Map<String , DetailData> fundMap = new HashMap<>();
        //更新Mongo数据
        List<DetailData> mongoData = mongoTemplate.findAll(DetailData.class);
        if(mongoData.size() > 0){
            String nowDateStr = DateUtils.parseDateToStr(DateUtils.YYYYMMDD , new Date());
            //利用mongbd中记录的时间对比是否更新过数据
            if(!Objects.equals(mongoData.get(0).getUpdateDate() , nowDateStr)){
                //删除所有数据
                mongoTemplate.dropCollection(DetailData.class);
                mongoData.parallelStream().forEach(detailData -> {
                    String code = detailData.getCode();
                    DetailData fundData = getFundData(code);
                    if(fundData != null){
                        fundData.setUpdateDate(nowDateStr);
                        //插入新数据
                        mongoTemplate.insert(fundData);
                        fundMap.put(code , fundData);
                    }
                });
            }
        }

        mongoTemplate.dropCollection(FundInfoVo.class);
        //获取MySQL中数据
        List<FundNetWorth> fundNetWorths = fundNetWorthService.selectFundNetWorthList(null);
        fundNetWorths.parallelStream().forEach(data -> {
            FundInfo fundInfo = new FundInfo();
            FundNetWorth fundNetWorth = new FundNetWorth();
            String code = data.getFundInfoCode();
            DetailData detailData = fundMap.get(code);
            if(detailData == null){
                detailData = getFundData(code);
            }
            if(detailData != null){
                BeanUtils.copyBeanProp(fundInfo , detailData);
                fundInfo.setUpdateTime(new Date());
                BeanUtils.copyBeanProp(fundNetWorth , detailData);
                fundNetWorth.setId(data.getId());
                fundInfoService.updateFundInfo(fundInfo);
                fundNetWorthService.updateFundNetWorth(fundNetWorth);
                //写入MongGO当作缓存使用
                FundInfoVo fundInfoVo = new FundInfoVo();
                BeanUtils.copyBeanProp(fundInfoVo , fundInfo);
                fundInfoVo.setFundNetWorth(fundNetWorth);
                mongoTemplate.insert(fundInfoVo);
                //按照基金类型分别处理
                //货币基金
                if(Objects.equals(FundType.currency.getValue() , detailData.getType())){
                    fundHoldService.formateCurrencyFund(detailData);
                }else{
                    //其他基金
                    fundHoldService.formateOtherFund(detailData);
                }
            }
        });
    }


    /**
     * 获取基金数据信息
     * @param code
     * @return
     */
    private DetailData getFundData(String code){
        FundDetailResponse v1FundDetail = FundData.getV1FundDetail(code);
        //请求成功
        if(Objects.equals(FundStatus.SUCCESS_200.getValue() , v1FundDetail.getCode())){
            return v1FundDetail.getData();
        }
        //请求数据失败
        return null;
    }
}
