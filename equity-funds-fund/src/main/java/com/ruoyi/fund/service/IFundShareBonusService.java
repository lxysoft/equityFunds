package com.ruoyi.fund.service;

import com.ruoyi.fund.domain.FundShareBonus;
import java.util.List;

/**
 * 基金分红记录Service接口
 * 
 * @author lxy
 * @date 2019-12-04
 */
public interface IFundShareBonusService 
{
    /**
     * 查询基金分红记录
     * 
     * @param id 基金分红记录ID
     * @return 基金分红记录
     */
    public FundShareBonus selectFundShareBonusById(Long id);

    /**
     * 查询基金分红记录列表
     * 
     * @param fundShareBonus 基金分红记录
     * @return 基金分红记录集合
     */
    public List<FundShareBonus> selectFundShareBonusList(FundShareBonus fundShareBonus);

    /**
     * 新增基金分红记录
     * 
     * @param fundShareBonus 基金分红记录
     * @return 结果
     */
    public int insertFundShareBonus(FundShareBonus fundShareBonus);

    /**
     * 修改基金分红记录
     * 
     * @param fundShareBonus 基金分红记录
     * @return 结果
     */
    public int updateFundShareBonus(FundShareBonus fundShareBonus);

    /**
     * 批量删除基金分红记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundShareBonusByIds(String ids);

    /**
     * 删除基金分红记录信息
     * 
     * @param id 基金分红记录ID
     * @return 结果
     */
    public int deleteFundShareBonusById(Long id);

    List<FundShareBonus> selectAll(FundShareBonus shareBonus);
}
