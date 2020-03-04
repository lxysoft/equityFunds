package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 11:19
 */
public enum FundType {
    /**
     * 基金类型
     */
    equity("股票型" , "股票型"),
    hybrid("混合型" , "混合型"),
    bond("指数型" , "债券型"),
    currency("货币型" , "货币型"),
    exponent("指数型" , "指数型"),
    QDII("QDII" , "QDII"),
    FOF("FOF" , "FOF")
    ;


    private final String value;
    private final String desc;

    FundType(String value, String desc) {
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
