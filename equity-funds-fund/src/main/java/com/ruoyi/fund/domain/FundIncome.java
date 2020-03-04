package com.ruoyi.fund.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;


/**
 * 基金历史收益对象 fund_income
 * 
 * @author lxy
 * @date 2019-12-06
 */
@Data
public class FundIncome extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 日期yyMMdd 6为int */
    private Integer netWorkDate;

    /** 基金代码 */
    @Excel(name = "基金代码")
    private String fundInfoCode;

    /** 持有金额 */
    @Excel(name = "持有金额")
    private Double holdMony;

    /** 持有份额 */
    @Excel(name = "持有份额")
    private Double holdTradeShare;

    /** 当前收益金额 */
    @Excel(name = "当前收益金额")
    private Double incomeMony;

    @Excel(name = "买入费用")
    private Double buyPoundage;

}
