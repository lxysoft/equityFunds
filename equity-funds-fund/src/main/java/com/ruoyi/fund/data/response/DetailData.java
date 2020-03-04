package com.ruoyi.fund.data.response;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:27
 */
@Data
@Document(collection = "fund_info")
public class DetailData {
    /**
     * 基金代码
     */
    @Id
    private String code;
    /**
     *基金名称
     */
    private String name;
    /**
     *基金类型
     */
    private String type;
    /**
     *当前基金单位净值
     */
    private Double netWorth;
    /**
     *当前基金单位净值估算
     */
    private Double expectWorth;
    /**
     *当前基金累计净值
     */
    private Double totalWorth;
    /**
     *当前基金单位净值估算日涨幅,单位为百分比
     */
    private String expectGrowth;
    /**
     *单位净值日涨幅,单位为百分比
     */
    private String dayGrowth;
    /**
     *单位净值周涨幅,单位为百分比
     */
    private String lastWeekGrowth;
    /**
     *单位净值月涨幅,单位为百分比
     */
    private String lastMonthGrowth;
    /**
     *单位净值三月涨幅,单位为百分比
     */
    private String lastThreeMonthGrowth;
    /**
     * 单位净值六月涨幅,单位为百分比
     */
    private String lastSixMonthGrowth;
    /**
     *单位净值年涨幅,单位为百分比
     */
    private String lastYearGrowth;
    /**
     * 起购额度
     */
    private Double buyMin;
    /**
     *原始买入费率,单位为百分比
     */
    private Double buySourceRate;
    /**
     *当前买入费率,单位为百分比
     */
    private Double buyRate;
    /**
     *基金经理
     */
    private String manager;
    /**
     *基金规模及日期,日期为最后一次规模变动的日期
     */
    private String fundScale;
    /**
     *净值更新日期,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
     */
    private Date worthDate;
    /**
     *净值估算更新日期,,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
     */
    private Date expectWorthDate;
    /**
     *历史净值信息["011218" , 1 , 0 , ""]依次表示:日期; 单位净值; 净值涨幅;每份分红.
     * 日期格式为yyMMdd,011218表示2001年12月18日
     */
    private List<List<String>> netWorthData;
    /**
     * 每万分收益(货币基金)
     */
    private Double millionCopiesIncome;
    /**
     *历史万每分收益信息(货币基金)["160923",0.4773]依次表示:日期; 每万分收益.
     * 日期格式为yyMMdd,160923表示2016年09月26日
     */
    private List<List<String>> millionCopiesIncomeData;
    /**
     *七日年化收益更新日期(货币基金)
     */
    private Date millionCopiesIncomeDate;
    /**
     * 七日年化收益(货币基金)
     */
    private Double sevenDaysYearIncome;
    /**
     *历史七日年华收益信息(货币基金)["160923",2.131]依次表示:日期; 七日收益.
     * 日期格式为yyMMdd,160923表示2016年09月26日
     */
    private List<List<String>> sevenDaysYearIncomeDate;

    /** 数据更新时间*/
     private String updateDate;
}
