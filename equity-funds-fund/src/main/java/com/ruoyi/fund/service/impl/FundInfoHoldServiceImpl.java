package com.ruoyi.fund.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.mapper.FundDaysNetWorthMapper;
import com.ruoyi.fund.mapper.FundHoldMapper;
import com.ruoyi.fund.mapper.FundIncomeMapper;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import com.ruoyi.fund.vo.FundInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundInfoHoldMapper;
import com.ruoyi.fund.domain.FundInfoHold;
import com.ruoyi.fund.service.IFundInfoHoldService;
import com.ruoyi.common.core.text.Convert;

/**
 * 持有基金信息关联表Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-03
 */
@Service
public class FundInfoHoldServiceImpl implements IFundInfoHoldService 
{
    @Autowired
    private FundInfoHoldMapper fundInfoHoldMapper;
    @Autowired
    private FundIncomeMapper fundIncomeMapper;
    @Autowired
    private FundHoldMapper fundHoldMapper;
    @Autowired
    private FundDaysNetWorthMapper fundDaysNetWorthMapper;

    /**
     * 查询持有基金信息关联表
     * 
     * @param id 持有基金信息关联表ID
     * @return 持有基金信息关联表
     */
    @Override
    public FundInfoHold selectFundInfoHoldById(String id)
    {
        return fundInfoHoldMapper.selectFundInfoHoldById(id);
    }

    /**
     * 查询持有基金信息关联表列表
     * 
     * @param fundInfo 持有基金信息关联表
     * @return 持有基金信息关联表
     */
    @Override
    public List<FundInfoVo> selectFundInfoHoldList(FundInfo fundInfo)
    {
        fundInfo.setCreateBy(ShiroUtils.getLoginName());
        List<FundInfoVo> fundInfoVos = fundInfoHoldMapper.selectFundInfoHoldListAll(fundInfo);
        //统计 持有金额 收益率 昨日收益
        fundInfoVos.stream().forEach(fundInfoVo -> {
            fundInfoVo.setShareBonusType(Objects.equals(fundInfoVo.getShareBonusType() , "1")?"转现":"再投资");
            //昨日收益金额
            Optional.ofNullable(fundIncomeMapper.selectLastDataByCreateAndCode(fundInfoVo.getCreateBy() , fundInfoVo.getCode()))
                .ifPresent(fundIncome -> fundInfoVo.setLastDayIncomMony(fundIncome.getIncomeMony()));
            //统计持有数据
            Optional.ofNullable(fundHoldMapper.statisticalByDateInt(null, fundInfoVo.getCode(), fundInfoVo.getCreateBy()))
                    .ifPresent(fundHold -> {
                        //购买初始金额
                        Double mony = fundHold.getMony();
                        //持有总份额
                        Double tradeShare = fundHold.getTradeShare();
                        //获取上一日的单位净值
                        FundDaysNetWorth fundDaysNetWorth = fundDaysNetWorthMapper.selectLastDateInfo(fundInfoVo.getCode(), "1");
                        //今日净值
                        Double netValueUnit = fundDaysNetWorth.getNetValueUnit();
                        //总持有金额
                        fundInfoVo.setHoldMony(Arith.round(tradeShare*netValueUnit , 2));
                        //收益金额
                        fundInfoVo.setIncomeMony(Arith.round(fundInfoVo.getHoldMony()-mony , 2));
                        //收益率
                        fundInfoVo.setYield(Arith.round(fundInfoVo.getIncomeMony()/mony , 2));

                    });
        });
        return fundInfoVos;
    }


    @Override
    public List<FundInfoHoldVo> selectAll(FundInfoHold fundInfoHold)
    {
        return fundInfoHoldMapper.selectFundInfoHoldList(fundInfoHold);
    }

    @Override
    public List<FundInfoHoldVo> selectAll()
    {
        return fundInfoHoldMapper.selectFundInfoHoldListJoinInfo();
    }
    /**
     * 新增持有基金信息关联表
     * 
     * @param fundInfoHold 持有基金信息关联表
     * @return 结果
     */
    @Override
    public int insertFundInfoHold(FundInfoHold fundInfoHold)
    {
        fundInfoHold.setCreateTime(DateUtils.getNowDate());
        return fundInfoHoldMapper.insertFundInfoHold(fundInfoHold);
    }

    /**
     * 修改持有基金信息关联表
     * 
     * @param fundInfoHold 持有基金信息关联表
     * @return 结果
     */
    @Override
    public int updateFundInfoHold(FundInfoHold fundInfoHold)
    {
        fundInfoHold.setUpdateTime(DateUtils.getNowDate());
        return fundInfoHoldMapper.updateFundInfoHold(fundInfoHold);
    }

    /**
     * 删除持有基金信息关联表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundInfoHoldByIds(String ids)
    {
        return fundInfoHoldMapper.deleteFundInfoHoldByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除持有基金信息关联表信息
     * 
     * @param id 持有基金信息关联表ID
     * @return 结果
     */
    @Override
    public int deleteFundInfoHoldById(String id)
    {
        return fundInfoHoldMapper.deleteFundInfoHoldById(id);
    }
}
