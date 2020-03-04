package com.ruoyi.fund.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.domain.FundNetWorth;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基金基础数据对象 fund_info
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Data
@Document(collection = "fund_info_vo")
public class FundInfoVo extends FundInfo
{
    private static final long serialVersionUID = 1L;
    private FundNetWorth fundNetWorth;
    @Excel(name = "分红类型")
    private String shareBonusType;
    @Excel(name = "持有金额")
    private Double holdMony;
    @Excel(name = "收益率")
    private Double yield;
    @Excel(name = "收益金额")
    private Double incomeMony;
    @Excel(name = "昨日收益金额")
    private Double lastDayIncomMony;
}
