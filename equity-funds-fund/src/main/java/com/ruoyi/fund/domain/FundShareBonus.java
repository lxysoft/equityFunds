package com.ruoyi.fund.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;


/**
 * 基金分红记录对象 fund_share_bonus
 * 
 * @author lxy
 * @date 2019-12-04
 */
@Data
public class FundShareBonus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 基金代码 */
    @Excel(name = "基金代码")
    private String fundInfoCode;

    /** 删除标记 */
    private String delFlag;

    /** 主键 */
    private String id;

    /** 分红类型 1红利转现 2红利再投资 */
    @Excel(name = "分红类型 1红利转现 2红利再投资")
    private String shareBonusType;

    /** 分红份额 */
    @Excel(name = "分红份额")
    private Double tradeShare;

    /** 每股分红金额 */
    @Excel(name = "每股分红金额")
    private Double shareBonusOneMoney;

    /** 总分红金额 */
    @Excel(name = "总分红金额")
    private Double shareBonusMoney;

    /** 分红时间*/
    private Integer shareBonusDate;

}
