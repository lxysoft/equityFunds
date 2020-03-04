package com.ruoyi.fund.domain;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 持有基金信息对象 fund_hold
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Data
public class FundHold extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 删除标记 */
    private String delFlag;

    /** 基金信息主键 */
    @Excel(name = "基金代码")
    private String fundInfoCode;

    /** 买入卖出金额 买入为正，卖出为负 */
    @Excel(name = "交易金额")
    private Double mony;

    /** 当前基金净值 */
    @Excel(name = "单位净值")
    private Double netValueUnit;

    /** 日期yyMMdd 6为int */
    //@Excel(name = "估值日期")
    private Integer netWorkDate;

    /** 交易份额 买为正卖为负 */
    @Excel(name = "交易份额")
    private Double tradeShare;

    /**红利再投资关联父ID*/
    private String fundHoldId;

    /** 分红类型 1红利转现 2红利再投资 */
    private String shareBonusType;

}
