package com.ruoyi.fund.service;

import com.ruoyi.fund.domain.FundNetWorth;
import java.util.List;

/**
 * 基金费率以及净值数据Service接口
 * 
 * @author lxy
 * @date 2019-11-29
 */
public interface IFundNetWorthService 
{
    /**
     * 查询基金费率以及净值数据
     * 
     * @param id 基金费率以及净值数据ID
     * @return 基金费率以及净值数据
     */
    public FundNetWorth selectFundNetWorthById(String id);

    /**
     * 查询基金费率以及净值数据列表
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 基金费率以及净值数据集合
     */
    public List<FundNetWorth> selectFundNetWorthList(FundNetWorth fundNetWorth);

    /**
     * 新增基金费率以及净值数据
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 结果
     */
    public int insertFundNetWorth(FundNetWorth fundNetWorth);

    /**
     * 修改基金费率以及净值数据
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 结果
     */
    public int updateFundNetWorth(FundNetWorth fundNetWorth);

    /**
     * 批量删除基金费率以及净值数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundNetWorthByIds(String ids);

    /**
     * 删除基金费率以及净值数据信息
     * 
     * @param id 基金费率以及净值数据ID
     * @return 结果
     */
    public int deleteFundNetWorthById(String id);
}
