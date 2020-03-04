package com.ruoyi.fund.service;

import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.domain.FundInfoHold;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import com.ruoyi.fund.vo.FundInfoVo;

import java.util.List;

/**
 * 持有基金信息关联表Service接口
 * 
 * @author ruoyi
 * @date 2019-12-03
 */
public interface IFundInfoHoldService 
{
    /**
     * 查询持有基金信息关联表
     * 
     * @param id 持有基金信息关联表ID
     * @return 持有基金信息关联表
     */
    public FundInfoHold selectFundInfoHoldById(String id);

    /**
     * 查询持有基金信息关联表列表
     * 
     * @param fundInfo 持有基金信息关联表
     * @return 持有基金信息关联表集合
     */
    public List<FundInfoVo> selectFundInfoHoldList(FundInfo fundInfo);

    /**
     * 新增持有基金信息关联表
     * 
     * @param fundInfoHold 持有基金信息关联表
     * @return 结果
     */
    public int insertFundInfoHold(FundInfoHold fundInfoHold);

    /**
     * 修改持有基金信息关联表
     * 
     * @param fundInfoHold 持有基金信息关联表
     * @return 结果
     */
    public int updateFundInfoHold(FundInfoHold fundInfoHold);

    /**
     * 批量删除持有基金信息关联表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundInfoHoldByIds(String ids);

    /**
     * 删除持有基金信息关联表信息
     * 
     * @param id 持有基金信息关联表ID
     * @return 结果
     */
    public int deleteFundInfoHoldById(String id);

    List<FundInfoHoldVo> selectAll(FundInfoHold fundInfoHold);
    List<FundInfoHoldVo> selectAll();
}
