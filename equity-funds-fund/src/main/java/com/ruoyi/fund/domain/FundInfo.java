package com.ruoyi.fund.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 基金基础数据对象 fund_info
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Data
public class FundInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 基金代码 */
    @Excel(name = "基金代码")
    @Id
    private String code;

    /** 基金名称 */
    @Excel(name = "基金名称")
    private String name;

    /** 基金类型 */
    @Excel(name = "基金类型")
    private String type;

    /** 起购额度 */
    @Excel(name = "起购额度")
    private Double buyMin;

    /** 原始买入费率,单位为百分比 */
    @Excel(name = "原始买入费率")
    private Double buySourceRate;

    /** 当前买入费率,单位为百分比 */
    @Excel(name = "当前买入费率")
    private Double buyRate;

    /** 基金经理 */
    @Excel(name = "基金经理")
    private String manager;

    /** 基金规模及日期,日期为最后一次规模变动的日期 */
    @Excel(name = "基金规模及日期")
    private String fundScale;

    /** 净值更新日期 */
    @Excel(name = "净值更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date worthDate;

    /** 净值估算更新日期, */
    @Excel(name = "净值估算更新日期,", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expectWorthDate;

    /** 每万分收益(货币基金) */
    @Excel(name = "每万分收益")
    private Double millionCopiesIncome;

    /** 七日年化收益更新日期(货币基金) */
    @Excel(name = "七日年化收益更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date millionCopiesIncomeDate;

    /** 七日年化收益(货币基金) */
    @Excel(name = "七日年化收益")
    private Double sevenDaysYearIncome;

    /** 删除标记 */
    private String delFlag;

}
