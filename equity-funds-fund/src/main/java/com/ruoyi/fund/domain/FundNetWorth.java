package com.ruoyi.fund.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基金费率以及净值数据对象 fund_net_worth
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Data
public class FundNetWorth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** fund_info_id 基金信息ID */
    private String fundInfoCode;

    /** 当前基金单位净值 */
    @Excel(name = "单位净值")
    private Double netWorth;

    /** 当前基金单位净值估算 */
    @Excel(name = "当前估算净值")
    private Double expectWorth;

    /** 当前基金累计净值 */
    @Excel(name = "累计净值")
    private Double totalWorth;

    /** 当前基金单位净值估算日涨幅,单位为百分比 */
    @Excel(name = "净值估算日涨幅")
    private String expectGrowth;

    /** 单位净值日涨幅,单位为百分比 */
    @Excel(name = "净值日涨幅")
    private String dayGrowth;

    /** 单位净值周涨幅,单位为百分比 */
    @Excel(name = "净值周涨幅")
    private String lastWeekGrowth;

    /** 单位净值月涨幅,单位为百分比 */
    @Excel(name = "净值月涨幅")
    private String lastMonthGrowth;

    /** 单位净值三月涨幅,单位为百分比 */
    @Excel(name = "净值三个月涨幅")
    private String lastThreeMonthGrowth;

    /** 单位净值六月涨幅,单位为百分比 */
    @Excel(name = "净值半年涨幅")
    private String lastSixMonthGrowth;

    /** 单位净值年涨幅,单位为百分比 */
    @Excel(name = "净值年涨幅")
    private String lastYearGrowth;

}
