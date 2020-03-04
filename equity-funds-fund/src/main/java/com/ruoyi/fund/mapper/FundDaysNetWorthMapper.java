package com.ruoyi.fund.mapper;

import com.ruoyi.fund.domain.FundDaysNetWorth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 记录基金每日净值数据Mapper接口
 * 
 * @author lxy
 * @date 2019-11-29
 */
public interface FundDaysNetWorthMapper
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
     * 删除记录基金每日净值数据
     * 
     * @param id 记录基金每日净值数据ID
     * @return 结果
     */
    public int deleteFundDaysNetWorthById(String id);

    /**
     * 批量删除记录基金每日净值数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundDaysNetWorthByIds(String[] ids);

    /**
     * 查询最后一天估值信息
     * @param code
     * @return
     */
    FundDaysNetWorth selectLastDateInfo(@Param("code")String code , @Param("limit")String limit);

    /**
     * 查询指定日期，指定基金的估值信息
     * @param fundInfoCode
     * @param netWorkDate
     * @return
     */
    FundDaysNetWorth findByDateAndCode(@Param("fundInfoCode") String fundInfoCode,@Param("netWorkDate") Integer netWorkDate);

    /**
     * 查询购入至今数据中有分红的数据信息
     * @param startDateInt
     * @param endDateInt
     * @return
     */
    List<FundDaysNetWorth> findHaveShareBonusData(@Param("startDateInt") Integer startDateInt
            , @Param("endDateInt") Integer endDateInt ,@Param("fundInfoCode") String fundInfoCode);

    List<FundDaysNetWorth> selectFundDaysNetWorthListAsc(FundDaysNetWorth fundDaysNetWorth);
}
