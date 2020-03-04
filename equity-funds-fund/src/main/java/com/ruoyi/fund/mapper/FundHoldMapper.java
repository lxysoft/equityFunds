package com.ruoyi.fund.mapper;

import com.ruoyi.fund.domain.FundHold;
import com.ruoyi.fund.vo.FundHoldVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 持有基金信息Mapper接口
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
public interface FundHoldMapper 
{
    /**
     * 查询持有基金信息
     * 
     * @param id 持有基金信息ID
     * @return 持有基金信息
     */
    public FundHold selectFundHoldById(String id);

    /**
     * 查询持有基金信息列表
     * 
     * @param fundHold 持有基金信息
     * @return 持有基金信息集合
     */
    public List<FundHoldVo> selectFundHoldList(FundHold fundHold);

    /**
     * 新增持有基金信息
     * 
     * @param fundHold 持有基金信息
     * @return 结果
     */
    public int insertFundHold(FundHold fundHold);

    /**
     * 修改持有基金信息
     * 
     * @param fundHold 持有基金信息
     * @return 结果
     */
    public int updateFundHold(FundHold fundHold);

    /**
     * 删除持有基金信息
     * 
     * @param id 持有基金信息ID
     * @return 结果
     */
    public int deleteFundHoldById(String id);

    /**
     * 批量删除持有基金信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFundHoldByIds(String[] ids);

    /**
     * 统计同基金持有份额是否可以卖出
     * @param fundInfoCode
     * @param id
     * @return
     */
    Double sumMonyByCode(@Param("fundInfoCode")String fundInfoCode,@Param("id") String id);

    void deleteByFunfHoldId(@Param("createBy")String createBy,@Param("fundInfoCode") String fundInfoCode);

    FundHold getEarliesDataByCode(String fundInfoCode);
    /**
     * 统计指定日期以前的数据 统计总份额和总金额
     * @param netWortDate
     * @param fundInfoCode
     * @param createBy
     * @return
     */
    FundHold statisticalByDateInt(@Param("netWortDate") Integer netWortDate,
                                  @Param("fundInfoCode") String fundInfoCode,
                                  @Param("createBy") String createBy);
}
