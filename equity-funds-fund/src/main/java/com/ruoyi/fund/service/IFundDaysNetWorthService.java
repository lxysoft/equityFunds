package com.ruoyi.fund.service;

import com.ruoyi.fund.domain.FundDaysNetWorth;
import java.util.List;

/**
 * 记录基金每日净值数据Service接口
 * 
 * @author lxy
 * @date 2019-11-29
 */
public interface IFundDaysNetWorthService 
{
    /**
     * 查询记录基金每日净值数据
     * 
     * @param id 记录基金每日净值数据ID
     * @return 记录基金每日净值数据
     */
    public FundDaysNetWorth selectFundDaysNetWorthById(String id);

    /**
     * 查询记录基金每日净值数据列表
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 记录基金每日净值数据集合
     */
    public List<FundDaysNetWorth> selectFundDaysNetWorthList(FundDaysNetWorth fundDaysNetWorth);

    /**
     * 新增记录基金每日净值数据
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 结果
     */
    public int insertFundDaysNetWorth(FundDaysNetWorth fundDaysNetWorth);

    /**
     * 修改记录基金每日净值数据
     * 
     * @param fundDaysNetWorth 记录基金每日净值数据
     * @return 结果
     */
    public int updateFundDaysNetWorth(FundDaysNetWorth fundDaysNetWorth);

    /**
     * 批量删除记录基金每日净值数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundDaysNetWorthByIds(String ids);

    /**
     * 删除记录基金每日净值数据信息
     * 
     * @param id 记录基金每日净值数据ID
     * @return 结果
     */
    public int deleteFundDaysNetWorthById(String id);

    /**
     * 查询购入至今数据中有分红的数据信息
     * @param startDateInt
     * @param endDateInt
     * @param fundInfoCode
     * @return
     */
    List<FundDaysNetWorth> findHaveShareBonusData(Integer startDateInt, Integer endDateInt, String fundInfoCode);

    List<FundDaysNetWorth> selectFundDaysNetWorthListAsc(FundDaysNetWorth fundDaysNetWorth);
}
