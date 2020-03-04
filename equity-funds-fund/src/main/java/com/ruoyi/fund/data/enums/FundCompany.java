package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 11:36
 */
public enum FundCompany {
    huaxia("华夏","华夏"),
    jiashi("嘉实","嘉实"),
    yifangda("易方达","易方达"),
    nanfang("南方","南方"),
    zhongyin("中银","中银"),
    guangfa("广发","广发"),
    gongyin("工银","工银"),
    boshi("博时","博时"),
    huaan("华安","华安"),
    huitianfu("汇添富","汇添富"),
    ;

    private final String value;
    private final String desc;

    FundCompany(String value, String desc) {
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
