package com.ruoyi.fund.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PatternUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.fund.data.enums.FundType;
import com.ruoyi.fund.data.response.DetailData;
import com.ruoyi.fund.domain.*;
import com.ruoyi.fund.mapper.*;
import com.ruoyi.fund.service.IFundHoldService;
import com.ruoyi.fund.vo.FundHoldVo;
import com.ruoyi.fund.vo.FundInfoVo;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 持有基金信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Service
public class FundHoldServiceImpl implements IFundHoldService
{
    @Autowired
    private FundHoldMapper fundHoldMapper;
    @Autowired
    private FundNetWorthMapper fundNetWorthMapper;
    @Autowired
    private FundInfoMapper fundInfoMapper;
    @Autowired
    private FundDaysNetWorthMapper fundDaysNetWorthMapper;
    @Autowired
    private FundInfoHoldMapper fundInfoHoldMapper;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private  FundShareBonusMapper fundShareBonusMapper;

    /**
     * 查询持有基金信息
     * 
     * @param id 持有基金信息ID
     * @return 持有基金信息
     */
    @Override
    public FundHoldVo selectFundHoldById(String id)
    {
        FundHold data = fundHoldMapper.selectFundHoldById(id);
        FundHoldVo fundHoldVo = new FundHoldVo();
        BeanUtils.copyBeanProp(fundHoldVo, data);
        fundHoldVo.setBuyType(fundHoldVo.getMony()>0?1:-1);
        fundHoldVo.setMony(fundHoldVo.getMony()*fundHoldVo.getBuyType());
        String relaYearDate = getRelaYearDate(fundHoldVo.getNetWorkDate());
        fundHoldVo.setNetWorkDateStr(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,DateUtils.parseDate(relaYearDate)));
        return fundHoldVo;
    }

    /**
     * 原始数据列表
     * @param fundHold
     * @return
     */
    @Override
    public List<FundHoldVo> selectAll (FundHold fundHold){
        return fundHoldMapper.selectFundHoldList(fundHold);
    }
    /**
     * 查询持有基金信息列表
     * 
     * @param fundHold 持有基金信息
     * @return 持有基金信息
     */
    @Override
    public List<FundHoldVo> selectFundHoldList(FundHold fundHold)
    {
        formatSelectFundHoldListParam(fundHold);
        fundHold.setCreateBy(ShiroUtils.getLoginName());
        List<FundHoldVo> fundHolds = fundHoldMapper.selectFundHoldList(fundHold);
        Map<String , Double> netWorthMap = new HashMap<>();
        Map<String , Double> dataCach = new HashMap<>();
        fundHolds.parallelStream().forEach(fundHoldVo -> {
            //获取最近日期基金的估值
            Double nowNetWorth = getDateNetWorth(netWorthMap , fundHoldVo.getFundInfoCode());
            if(nowNetWorth != null){
                Date netWorkDate = DateUtils.parseDate(getRelaYearDate(fundHoldVo.getNetWorkDate()));
                //外显交易时间
                fundHoldVo.setNetWorkDateShow(netWorkDate);
                fundHoldVo.setNetWorkDateStr(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD ,netWorkDate));
                if(fundHoldVo.getMony() > 0){
                    Double lastValueUnitData = getLastLastValueUnitData(dataCach, fundHoldVo.getFundInfoCode());
                    if(lastValueUnitData != null){
                        //上交易日收益
                        double lastcomeAmount = Arith.round((nowNetWorth - lastValueUnitData) * fundHoldVo.getTradeShare(), 2);
                        fundHoldVo.setLastNetValueUnit(lastcomeAmount);
                    }
                    // 收益金额 = （估值 * 份数）-购入金额
                    double incomeAmount = Arith.round(nowNetWorth * fundHoldVo.getTradeShare() - fundHoldVo.getMony(), 2);
                    double theYield = Arith.round(incomeAmount / fundHoldVo.getMony() * 100, 2);
                    //今日净值
                    fundHoldVo.setNowNetValueUnit(nowNetWorth);
                    //收益金额
                    fundHoldVo.setIncomeAmount(incomeAmount);
                    //持有总额
                    fundHoldVo.setTotalAmount(Arith.round(fundHoldVo.getMony()+incomeAmount , 2));
                    //总收益率
                    fundHoldVo.setTheYield(theYield);
                    Double years = DateUtils.yearCompare(netWorkDate , new Date());
                    //持有时间
                    fundHoldVo.setHoldDays(years+"年");
                    if(years >= 1){
                        //年化收益率
                        fundHoldVo.setAnnualYield(Arith.round(theYield/years , 2)+"%");
                    }
                }
            }
        });
        return fundHolds;
    }

    /**
     * 查询上上日的净值数据
     */
    private Double getLastLastValueUnitData(Map<String , Double> dataCach , String fundInfoCode){
        Double result = dataCach.get(fundInfoCode);
        boolean present = Optional.ofNullable(result).isPresent();
        if(present){
            return result;
        }
        //倒数第二日净值数据
        FundDaysNetWorth fundDaysNetWorth = selectLastDateInfo(fundInfoCode, "1,1");
        if(fundDaysNetWorth != null){
            dataCach.put(fundInfoCode , fundDaysNetWorth.getNetValueUnit());
            return fundDaysNetWorth.getNetValueUnit();
        }
        return null;
    }

    private void formatSelectFundHoldListParam(FundHold fundHold) {
        Map<String, Object> params = fundHold.getParams();
        String netWorkDateStart = String.valueOf(params.get("netWorkDateStart"));
        String netWorkDateEnd = String.valueOf(params.get("netWorkDateEnd"));
        if(StringUtils.isNoneBlank(netWorkDateStart, netWorkDateEnd)){
            params.put("netWorkDateStart",netWorkDateStart.replaceAll("-" , "").substring(2));
            params.put("netWorkDateEnd",netWorkDateEnd.replaceAll("-" , "").substring(2));
        }else{
            params.put("netWorkDateStart",null);
            params.put("netWorkDateEnd",null);
        }
    }

    /**
     * 将yyMMdd时间处理成yyyyMMDD
     * @param netWorkDateInt
     * @return
     */
    private String getRelaYearDate(Integer netWorkDateInt){
        String netWorkDate = String.valueOf(netWorkDateInt);
        int year = LocalDate.now().getYear();
        if(netWorkDate.length() == 5){
            return "200"+netWorkDate;
        }else{
            String calculateStr = "20"+netWorkDate;
            Integer oldYear = Integer.valueOf(calculateStr.substring(0, 4));
            if(year < oldYear){
                return "19"+netWorkDate;
            }else{
                return "20"+netWorkDate;
            }
        }
    }

    /**
     * 获取最后一日估值 并在MAP中缓存
     * @param netWorthMap
     * @param fundInfoCode
     * @return
     */
    private Double getDateNetWorth(Map<String, Double> netWorthMap, String fundInfoCode) {
        Double result = netWorthMap.get(fundInfoCode);
        boolean present = Optional.ofNullable(result).isPresent();
        if(present){
            return result;
        }
        FundDaysNetWorth fundDaysNetWorth = selectLastDateInfo(fundInfoCode);
        if(fundDaysNetWorth != null){
            netWorthMap.put(fundInfoCode , fundDaysNetWorth.getNetValueUnit());
            return fundDaysNetWorth.getNetValueUnit();
        }
        return null;
    }


    /**
     * 新增持有基金信息
     * 
     * @param fundHold 持有基金信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertAndEditFundHold(FundHoldVo fundHold)
    {
        Optional<FundHoldVo> fundHoldOptional = Optional.ofNullable(fundHold);
        if(fundHoldOptional.isPresent()){

            fundHold.setCreateTime(DateUtils.getNowDate());
            Double money = fundHoldOptional.map(FundHoldVo::getMony).orElse(0d);
            Integer buyType = fundHoldOptional.map(FundHoldVo::getBuyType).orElse(1)>0?1:-1;
            fundHold.setMony(money*buyType);

            //处理获取正确的code
            Optional<String> codeOptional = fundHoldOptional.map(FundHoldVo::getFundInfoCode);
            if(codeOptional.isPresent()){
                String code = codeOptional.get();
                if(code.contains("-")){
                    fundHold.setFundInfoCode(code.split("-")[0]);
                }else{
                    return AjaxResult.error("请选择正确的基金信息");
                }
            }

            //判断是否可以卖出
            if(buyType < 0){
                Double sumMonyByCode = fundHoldMapper.sumMonyByCode(fundHold.getFundInfoCode() , fundHold.getId());
                if(sumMonyByCode - money < 0){
                    return AjaxResult.error("请重新输入卖出金额，当前可用卖出金额为："+sumMonyByCode);
                }
            }

            //处理时间格式
            fundHoldOptional.map(FundHoldVo::getNetWorkDateStr).ifPresent(dateStr -> {
                String networkDate = dateStr.substring(2).replace("-", "");
                //验证是否位数字
                if(PatternUtils.isInteger(networkDate)){
                    fundHold.setNetWorkDate(Integer.valueOf(networkDate));
                }
            });

            //处理fundInfoHold 表中数据
            SysUser sysUser = ShiroUtils.getSysUser();
            judgeFundInfoHoldHashData(fundHold , ShiroUtils.getLoginName());

            //处理数据 拆分数据
            //非货币基金时的操作setNetValueUnit and setTradeShare
            //存入当前日期单位净值 和  交易份额
            foramteData(fundHold.getFundInfoCode() , fundHold , sysUser.getLoginName());
            fundHold.setCreateTime(new Date());

            if(StringUtils.isBlank(fundHold.getId())){
                fundHold.setId(UUID.randomUUID().toString());
                fundHold.setCreateBy(sysUser.getLoginName());
                fundHold.setCreateTime(new Date());
                return AjaxResult.success(fundHoldMapper.insertFundHold(fundHold));
            }else{
                fundHold.setUpdateBy(sysUser.getLoginName());
                fundHold.setUpdateTime(new Date());
                //更新情况下删除基金分红、再投入的记录
                //删除旧数据 等待定时任务重新生成
                fundShareBonusMapper.deleteByFunfHoldId(sysUser.getLoginName() , fundHold.getFundInfoCode());
                fundHoldMapper.deleteByFunfHoldId(sysUser.getLoginName() , fundHold.getFundInfoCode());
                return AjaxResult.success(fundHoldMapper.updateFundHold(fundHold));
            }
        }
        return AjaxResult.error("请填写基金交易信息");
    }

    /**
     * 处理公共基金详情数据表
     * @param fundInfoCode
     * @param fundHold
     * @param loginName
     */
    private void foramteData(String fundInfoCode, FundHoldVo fundHold, String loginName) {
        //判断是否已经存在数据
        FundInfo fundInfo = fundInfoMapper.selectFundInfoById(fundInfoCode);
        if(fundInfo == null){
            //查询mogo中的基金详细数据
            Query query = new Query();
            query.addCriteria(Criteria.where("code").is(fundInfoCode));
            //基金详情
            DetailData detailData = mongoTemplate.findOne(query, DetailData.class);
            //基础信息对象
            fundInfo = new FundInfo();
            //净值估值对象
            FundNetWorth fundNetWorth = new FundNetWorth();
            //数据copy
            BeanUtils.copyBeanProp(fundInfo , detailData);
            fundInfo.setCreateTime(new Date());
            BeanUtils.copyBeanProp(fundNetWorth , detailData);
            //数据存入数据库
            fundInfoMapper.insertFundInfo(fundInfo);
            fundNetWorth.setId(UUID.randomUUID().toString());
            fundNetWorth.setFundInfoCode(fundInfoCode);
            fundNetWorthMapper.insertFundNetWorth(fundNetWorth);
            //写入MongGO当作缓存使用
            FundInfoVo fundInfoVo = new FundInfoVo();
            BeanUtils.copyBeanProp(fundInfoVo , fundInfo);
            fundInfoVo.setFundNetWorth(fundNetWorth);
            mongoTemplate.insert(fundInfoVo);
            //按照基金类型分别处理
            //货币基金
            if(Objects.equals(FundType.currency.getValue() , detailData.getType())){
                formateCurrencyFund(detailData);
            }else{
                //其他基金
                formateOtherFund(detailData);
            }
        }
        //非货币基金时的操作setNetValueUnit and setTradeShare
        //存入当前日期单位净值 和  交易份额
        if(!Objects.equals(FundType.currency.getValue() , fundInfo.getType())){
            Integer netWorkDate = fundHold.getNetWorkDate();
            LocalDate parseDate = LocalDate.parse(fundHold.getNetWorkDateStr());
            //用于计数
            Integer num = 0;
            FundDaysNetWorth fundDaysNetWorth = recursiveFindFundDaysNetWorth(netWorkDate, fundInfoCode, num , parseDate);
            if(fundDaysNetWorth != null){
                Double netValueUnit = fundDaysNetWorth.getNetValueUnit();
                fundHold.setNetValueUnit(netValueUnit);
                fundHold.setTradeShare(fundHold.getMony()/netValueUnit);
                //
            }else{
                //估值为查询到，返回异常回滚
                throw new BusinessException("未查询到对应日期基金估值。");
            }
        }
    }

    /**
     * 递归查询日期估值 ，防止节假日无估值情况
     * @param netWorkDate
     * @param fundInfoCode
     * @param num
     * @param parseDate
     * @return
     */
    private FundDaysNetWorth recursiveFindFundDaysNetWorth(Integer netWorkDate, String fundInfoCode, Integer num, LocalDate parseDate){
        FundDaysNetWorth fundDaysNetWorth = fundDaysNetWorthMapper.findByDateAndCode(fundInfoCode , netWorkDate);
        if(num < 12 && fundDaysNetWorth == null){
            num++;
            parseDate = parseDate.plusDays(1);
            netWorkDate = Integer.valueOf(parseDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(2));
            return recursiveFindFundDaysNetWorth(netWorkDate, fundInfoCode, num ,parseDate);
        }
        return fundDaysNetWorth;
    }

    /**
     * 货币基金交易处理
     * @param detailData
     */
    @Override
    public void formateCurrencyFund(DetailData detailData) {
        String code = detailData.getCode();
        //获取最后一天数据
        FundDaysNetWorth lastDate = selectLastDateInfo(code);
        Integer lastDateInt = 0;
        //如果没数据，则直接插入全部
        boolean canInsertAll = true;
        if(lastDate != null){
            canInsertAll = false;
            lastDateInt = lastDate.getNetWortDate();
        }

        //万元收益列表
        List<List<String>> millionCopiesIncomeData = detailData.getMillionCopiesIncomeData();
        //七日年化收益列表
        List<List<String>> sevenDaysYearIncomeDate = detailData.getSevenDaysYearIncomeDate();
        FundDaysNetWorth fundDaysNetWorth;
        if(millionCopiesIncomeData.size() ==sevenDaysYearIncomeDate.size()){
            for (int i = 0; i < millionCopiesIncomeData.size(); i++) {
                List<String> million = millionCopiesIncomeData.get(i);
                List<String> seven = sevenDaysYearIncomeDate.get(i);
                if(million.size() > 1 && seven.size() > 1){
                    Integer dateInt = Integer.valueOf(million.get(0));
                    if(canInsertAll || lastDateInt < dateInt){
                        fundDaysNetWorth = FundDaysNetWorth.builder()
                                .id(UUID.randomUUID().toString())
                                .fundInfoCode(code)
                                .netWortDate(dateInt)
                                .sevenDaysYearIncome(Double.valueOf(seven.get(1)))
                                .millionCopiesIncome(Double.valueOf(million.get(1))).build();
                        fundDaysNetWorthMapper.insertFundDaysNetWorth(fundDaysNetWorth);
                    }
                }
            }
        }
    }

    /**
     * 其他类型基金交易处理
     * @param detailData
     */
    @Override
    public void formateOtherFund(DetailData detailData) {
        String code = detailData.getCode();
        //获取最后一天数据
        FundDaysNetWorth lastDate = selectLastDateInfo(code);
        Integer lastDateInt = 0;
        //如果没数据，则直接插入全部
        boolean canInsertAll = true;
        if(lastDate != null){
            canInsertAll = false;
            lastDateInt = lastDate.getNetWortDate();
        }
        List<List<String>> netWorthData = detailData.getNetWorthData();
        for (List<String> data:netWorthData) {
            if(data.size() > 3){
                Integer dateInt = Integer.valueOf(data.get(0));
                if(canInsertAll || lastDateInt < dateInt){
                    FundDaysNetWorth fundDaysNetWorth = FundDaysNetWorth.builder()
                            .id(UUID.randomUUID().toString())
                            .fundInfoCode(code)
                            .netWortDate(dateInt)
                            .netValueUnit(Double.valueOf(data.get(1)))
                            .netGains(Double.valueOf(data.get(2)))
                            .eachShareBonus(data.get(3)).build();
                    fundDaysNetWorthMapper.insertFundDaysNetWorth(fundDaysNetWorth);
                }
            }
        }

    }

    /**
     * 简单数据插入
     * @param fundHold
     */
    @Override
    public void insertFundHold(FundHold fundHold) {
        fundHoldMapper.insertFundHold(fundHold);
    }

    /**
     * 处理持有基金数据表内容
     * @param fundHold
     * @param loginName
     */
    private void judgeFundInfoHoldHashData(FundHoldVo fundHold, String loginName) {
        String fundInfoCode = fundHold.getFundInfoCode();
        String shareBonusType = fundHold.getShareBonusType();
        FundInfoHold fundInfoHold = fundInfoHoldMapper.selectFundInfoHoldById(fundInfoCode);
        if(fundInfoHold == null){
            fundInfoHold = new FundInfoHold();
            fundInfoHold.setFundInfoCode(fundInfoCode);
            fundInfoHold.setCreateBy(loginName);
            fundInfoHold.setCreateTime(new Date());
            if(StringUtils.isNotBlank(shareBonusType)){
                fundInfoHold.setShareBonusType(shareBonusType);
            }
            fundInfoHoldMapper.insertFundInfoHold(fundInfoHold);
        }else if(StringUtils.isNotBlank(shareBonusType)){
            fundInfoHold.setShareBonusType(shareBonusType);
            fundInfoHoldMapper.updateFundInfoHold(fundInfoHold);
        }
    }

    /**
     * 自定义查询最后几天数据  limit 1,1 倒数第二天数据
     * @param code
     * @param limit
     * @return
     */
    @Override
    public FundDaysNetWorth selectLastDateInfo(String code , String limit) {
        return fundDaysNetWorthMapper.selectLastDateInfo(code , limit);
    }
    /**
     * 查询每日详细数据 的最近一条记录
     * @param code
     * @return
     */
    @Override
    public FundDaysNetWorth selectLastDateInfo(String code) {
        return fundDaysNetWorthMapper.selectLastDateInfo(code , "1");
    }

   /**
     * 修改持有基金信息
     * 
     * @param fundHold 持有基金信息
     * @return 结果
     */
    @Override
    public int updateFundHold(FundHold fundHold)
    {
        fundHold.setUpdateTime(DateUtils.getNowDate());

        return fundHoldMapper.updateFundHold(fundHold);
    }

    @Override
    public FundHold getEarliesDataByCode(String fundInfoCode) {
        return fundHoldMapper.getEarliesDataByCode(fundInfoCode);
    }

    /**
     * 统计指定日期以前的数据 统计总份额和总金额
     * @param netWortDate
     * @param fundInfoCode
     * @param createBy
     * @return
     */
    @Override
    public FundHold statisticalByDateInt(Integer netWortDate, String fundInfoCode, String createBy) {
        return fundHoldMapper.statisticalByDateInt(netWortDate , fundInfoCode, createBy);
    }

    /**
     * 删除持有基金信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
   /* @Override
    public int deleteFundHoldByIds(String ids)
    {
        return fundHoldMapper.deleteFundHoldByIds(Convert.toStrArray(ids));
    }
*/
    /**
     * 删除持有基金信息信息
     * 
     * @param id 持有基金信息ID
     * @return 结果
     */
    /*@Override
    public int deleteFundHoldById(String id)
    {
        return fundHoldMapper.deleteFundHoldById(id);
    }*/
}
