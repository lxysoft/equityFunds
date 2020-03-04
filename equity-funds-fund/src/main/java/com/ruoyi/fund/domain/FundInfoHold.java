package com.ruoyi.fund.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基金信息持有关联表对象 fund_info_hold
 * 
 * @author ruoyi
 * @date 2019-12-04
 */
@Data
public class FundInfoHold extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** fund_info 基金代码 */
    private String fundInfoCode;

    /** 删除标记 */
    private String delFlag;

    /** 分红类型 1红利转现 2红利再投资 */
    @Excel(name = "分红类型")
    private String shareBonusType;
}
