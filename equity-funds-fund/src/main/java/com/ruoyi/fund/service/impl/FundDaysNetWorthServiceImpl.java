package com.ruoyi.fund.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundDaysNetWorthMapper;
import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.service.IFundDaysNetWorthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 记录基金每日净值数据Service业务层处理
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Service
public class FundDaysNetWorthServiceImpl implements IFundDaysNetWorthService
{
    @Autowired
    private FundDaysNetWorthMapper fundDaysNetWorthMapper;

    /**
     * 查询记录基金每日净值数据
     * 
     * @param id 记录基金每日净值数据ID
     * @return 记录基金每日净值数据
     */
    @Override
    public FundDaysNetWorth selectFundDaysNetWorthById(String id)
    {
        return fundDaysNetWorthMapper.selectFundDaysNetWorthById(id);
    }

    /**
     * 查询记录基金每日净值数据列表
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 记录基金每日净值数据
     */
    @Override
    public List<FundDaysNetWorth> selectFundDaysNetWorthList(FundDaysNetWorth fundDaysNetWorth)
    {
        return fundDaysNetWorthMapper.selectFundDaysNetWorthList(fundDaysNetWorth);
    }

    /**
     * 新增记录基金每日净值数据
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 结果
     */
    @Override
    public int insertFundDaysNetWorth(FundDaysNetWorth fundDaysNetWorth)
    {
        return fundDaysNetWorthMapper.insertFundDaysNetWorth(fundDaysNetWorth);
    }

    /**
     * 修改记录基金每日净值数据
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 结果
     */
    @Override
    public int updateFundDaysNetWorth(FundDaysNetWorth fundDaysNetWorth)
    {
        return fundDaysNetWorthMapper.updateFundDaysNetWorth(fundDaysNetWorth);
    }

    /**
     * 删除记录基金每日净值数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundDaysNetWorthByIds(String ids)
    {
        return fundDaysNetWorthMapper.deleteFundDaysNetWorthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除记录基金每日净值数据信息
     * 
     * @param id 记录基金每日净值数据ID
     * @return 结果
     */
    @Override
    public int deleteFundDaysNetWorthById(String id)
    {
        return fundDaysNetWorthMapper.deleteFundDaysNetWorthById(id);
    }


    /**
     * 查询购入至今数据中有分红的数据信息
     * @param startDateInt
     * @param endDateInt
     * @param fundInfoCode
     * @return
     */
    @Override
    public List<FundDaysNetWorth> findHaveShareBonusData(Integer startDateInt, Integer endDateInt, String fundInfoCode) {
        return fundDaysNetWorthMapper.findHaveShareBonusData(startDateInt, endDateInt, fundInfoCode);
    }

    @Override
    public List<FundDaysNetWorth> selectFundDaysNetWorthListAsc(FundDaysNetWorth fundDaysNetWorth) {
        return fundDaysNetWorthMapper.selectFundDaysNetWorthListAsc(fundDaysNetWorth);
    }
}
