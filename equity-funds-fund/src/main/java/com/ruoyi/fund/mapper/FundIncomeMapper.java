package com.ruoyi.fund.mapper;

import com.ruoyi.fund.domain.FundIncome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基金历史收益Mapper接口
 * 
 * @author lxy
 * @date 2019-12-06
 */
public interface FundIncomeMapper 
{
    /**
     * 查询基金历史收益
     * 
     * @param id 基金历史收益ID
     * @return 基金历史收益
     */
    public FundIncome selectFundIncomeById(String id);

    /**
     * 查询基金历史收益列表
     * 
     * @param fundIncome 基金历史收益
     * @return 基金历史收益集合
     */
    public List<FundIncome> selectFundIncomeList(FundIncome fundIncome);

    /**
     * 新增基金历史收益
     * 
     * @param fundIncome 基金历史收益
     * @return 结果
     */
    public int insertFundIncome(FundIncome fundIncome);

    /**
     * 修改基金历史收益
     * 
     * @param fundIncome 基金历史收益
     * @return 结果
     */
    public int updateFundIncome(FundIncome fundIncome);

    /**
     * 删除基金历史收益
     * 
     * @param id 基金历史收益ID
     * @return 结果
     */
    public int deleteFundIncomeById(String id);

    /**
     * 批量删除基金历史收益
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundIncomeByIds(String[] ids);

    /**
     * 查询fundInfoHold中按创建人、基金代码 最晚的收益记录日期
     * @param createBy
     * @param fundInfoCode
     * @return
     */
    FundIncome selectLastDataByCreateAndCode(@Param("createBy") String createBy, @Param("fundInfoCode") String fundInfoCode);
}
