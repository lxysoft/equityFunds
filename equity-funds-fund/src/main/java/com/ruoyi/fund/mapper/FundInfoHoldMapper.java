package com.ruoyi.fund.mapper;

import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.domain.FundInfoHold;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import com.ruoyi.fund.vo.FundInfoVo;

import java.util.List;

/**
 * 持有基金信息关联表Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-03
 */
public interface FundInfoHoldMapper 
{
    /**
     * 查询持有基金信息关联表
     * 
     * @param fundInfoCode 持有基金编码
     * @return 持有基金信息关联表
     */
    public FundInfoHold selectFundInfoHoldById(String fundInfoCode);

    /**
     * 查询持有基金信息关联表列表
     * 
     * @param fundInfoHold 持有基金信息关联表
     * @return 持有基金信息关联表集合
     */
    public List<FundInfoHoldVo> selectFundInfoHoldList(FundInfoHold fundInfoHold);
    public List<FundInfoVo> selectFundInfoHoldListAll(FundInfo fundInfo);

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
     * 删除持有基金信息关联表
     * 
     * @param fundInfoCode 持有基金信息关联表ID
     * @return 结果
     */
    public int deleteFundInfoHoldById(String fundInfoCode);

    /**
     * 批量删除持有基金信息关联表
     * 
     * @param fundInfoCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundInfoHoldByIds(String[] fundInfoCodes);

    List<FundInfoHoldVo> selectFundInfoHoldListJoinInfo();
}
