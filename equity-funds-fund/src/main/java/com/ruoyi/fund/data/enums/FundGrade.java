package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 11:36
 */
public enum FundGrade {
    szwx("上证五星","上证五星"),
    zswz("招商五星","招商五星"),
    jawx("济安五星","济安五星"),
    ;

    private final String value;
    private final String desc;

    FundGrade(String value, String desc) {
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
