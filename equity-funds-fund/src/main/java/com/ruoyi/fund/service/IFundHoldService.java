package com.ruoyi.fund.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.fund.data.response.DetailData;
import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.domain.FundHold;
import com.ruoyi.fund.vo.FundHoldVo;

import java.util.List;

/**
 * 持有基金信息Service接口
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
public interface IFundHoldService 
{
    /**
     * 原始数据列表
     * @param fundHold
     * @return
     */
    List<FundHoldVo> selectAll (FundHold fundHold);
    /**
     * 查询持有基金信息
     * 
     * @param id 持有基金信息ID
     * @return 持有基金信息
     */
    public FundHoldVo selectFundHoldById(String id);

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
    public AjaxResult insertAndEditFundHold(FundHoldVo fundHold);
    /**
     * 查询每日详细数据 的最近一条记录
     * @param code
     * @return
     */
    public FundDaysNetWorth selectLastDateInfo(String code);
    /**
     * 自定义查询最后几天数据  limit 1,1 倒数第二天数据
     * @param code
     * @param limit
     * @return
     */
    public FundDaysNetWorth selectLastDateInfo(String code , String limit);
    /**
     * 货币基金交易处理
     * @param detailData
     */
    void formateCurrencyFund(DetailData detailData);
    /**
     * 其他类型基金交易处理
     * @param detailData
     */
    void formateOtherFund(DetailData detailData);

    /**
     * 简单插入数据
     * @param fundHold
     */
    void insertFundHold(FundHold fundHold);

    /**
     * 修改持有基金信息
     * 
     * @param fundHold 持有基金信息
     * @return 结果
     */
    public int updateFundHold(FundHold fundHold);

    /**
     * 查询该基金最早的购买记录
     * @param fundInfoCode
     * @return
     */
    FundHold getEarliesDataByCode(String fundInfoCode);

    /**
     * 统计指定日期以前的数据 统计总份额和总金额
     * @param netWortDate
     * @param fundInfoCode
     * @param createBy
     * @return
     */
    FundHold statisticalByDateInt(Integer netWortDate, String fundInfoCode, String createBy);

    /**
     * 批量删除持有基金信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    /*public int deleteFundHoldByIds(String ids);*/

    /**
     * 删除持有基金信息信息
     * 
     * @param id 持有基金信息ID
     * @return 结果
     */
    /*public int deleteFundHoldById(String id);*/
}
