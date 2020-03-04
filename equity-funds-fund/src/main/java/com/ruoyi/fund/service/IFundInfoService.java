package com.ruoyi.fund.service;

import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.vo.FundInfoVo;

import java.util.List;

/**
 * 基金基础数据Service接口
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
public interface IFundInfoService 
{
    /**
     * 查询基金基础数据
     * 
     * @param code 基金基础数据ID
     * @return 基金基础数据
     */
    public FundInfo selectFundInfoById(String code);

    /**
     * 查询基金基础数据列表
     * 
     * @param fundInfo 基金基础数据
     * @return 基金基础数据集合
     */
    public List<FundInfoVo> selectFundInfoList(FundInfo fundInfo);

    /**
     * 新增基金基础数据
     * 
     * @param fundInfo 基金基础数据
     * @return 结果
     */
    public int insertFundInfo(FundInfo fundInfo);

    /**
     * 修改基金基础数据
     * 
     * @param fundInfo 基金基础数据
     * @return 结果
     */
    public int updateFundInfo(FundInfo fundInfo);

    /**
     * 批量删除基金基础数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundInfoByIds(String ids);

    /**
     * 删除基金基础数据信息
     * 
     * @param code 基金基础数据ID
     * @return 结果
     */
    public int deleteFundInfoById(String code);
}
