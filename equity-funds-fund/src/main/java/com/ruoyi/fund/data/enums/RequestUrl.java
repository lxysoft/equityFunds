package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 9:50
 */
public enum RequestUrl {
    PostV1FundRank("https://api.doctorxiong.club/v1/fund/rank","获取基金排行"),
    GetV1FundDetail("https://api.doctorxiong.club/v1/fund/detail","获取基金详情"),
    GetV1FundHot("https://api.doctorxiong.club/v1/fund/hot","获取热门基金")
    ;

    private final String value;
    private final String desc;

    RequestUrl(String value, String desc) {
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
