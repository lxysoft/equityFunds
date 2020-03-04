package com.ruoyi.fund.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 记录基金每日净值数据对象 fund_days_net_worth
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Data
@Builder
public class FundDaysNetWorth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** fund_info主键 */
    private String fundInfoCode;

    /** 净值日期 */
    private Integer netWortDate;

    /** 单位净值 */
    private Double netValueUnit;

    /** 净值涨幅 */
    private Double netGains;

    /** 每股分红 */
    private String eachShareBonus;

    /** 七日年化收益率（货币基金） */
    private Double sevenDaysYearIncome;

    /** 当日万元收益（货币基金） */
    private Double millionCopiesIncome;

}
