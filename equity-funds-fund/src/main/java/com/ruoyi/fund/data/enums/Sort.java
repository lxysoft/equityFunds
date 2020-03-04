package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 11:31
 */
public enum Sort {
    /**
     * 排序方式
     */
    lastWeek("lastWeek","最近一周"),
    lastMonth("lastMonth","最近一个月"),
    lastThreeMonth("lastThreeMonth","最近三个月"),
    lastSixMonth("lastSixMonth","最近六个月"),
    thisYear("thisYear","当年"),
    lastYear("lastYear","最近一年"),
    lastTwoYears("lastTwoYears","最近两年"),
    lastThreeYears("lastThreeYears","最近三年"),
    ;

    private final String value;
    private final String desc;

    Sort(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
