package com.ruoyi.fund.data.response;

import lombok.Data;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:11
 * 基金数据信息
 */
@Data
public class RankAndHotData{
    /**
     * 基金代码
     */
    private String code;
    /**
     *基金名称
     */
    private String name;
    /**
     *当前基金单位净值
     */
    private String netWorth;
    /**
     *
     * 日涨幅,单位为百分比
     */
    private String dayGrowth;
    /**
     *最近一周涨幅,单位为百分比
     */
    private String lastWeekGrowth;
    /**
     *最近一个月涨幅,单位为百分比
     */
    private String lastMonthGrowth;
    /**
     *
     * 最近三个月涨幅,单位为百分比
     */
    private String lastThreeMonthGrowth;
    /**
     *最近六个月涨幅,单位为百分比
     */
    private String lastSixMonthGrowth;
    /**
     * 最近一年涨幅,单位为百分比
     */
    private String lastYearGrowth;
    /**
     * 今年的涨幅,单位为百分比
     */
    private String thisYearGrowth;

}
