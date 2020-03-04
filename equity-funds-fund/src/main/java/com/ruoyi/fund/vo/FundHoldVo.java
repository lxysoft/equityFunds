package com.ruoyi.fund.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.fund.domain.FundHold;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaoyang.li
 * @date 2019/12/2 9:47
 */
@Data
public class FundHoldVo extends FundHold {
    /**
     * 请求参数 交易类型
     */
    private int buyType;
    private String netWorkDateStr;





    /**
     * 返回参数
     */
    @Excel(name = "收益金额")
    private Double incomeAmount;
    @Excel(name = "持有总额")
    private Double totalAmount;
    @Excel(name = "总收益率")
    private Double theYield;
    @Excel(name = "持有时间")
    private String holdDays;
    @Excel(name = "年化收益率")
    private String annualYield;
    @Excel(name = "交易时间")
    private Date netWorkDateShow;
    @Excel(name = "基金名称")
    private String name;
    /**上次估值*/
    private Double lastNetValueUnit;
    /**
     * 最近估值
     */
    private Double nowNetValueUnit;

}
