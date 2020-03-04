package com.ruoyi.fund.data.enums;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 11:31
 */
public enum FundStatus {
    /**
     * 排序方式
     */
    SUCCESS_200(200,"成功"),
    ERROR_202(202,"没有符合条件的基金"),
    ERROR_400(400,"解析请求失败,参数错误"),
    ERROR_405(405,"无效的基金代码"),
    ERROR_500(500,"内部网络异常"),
    ;

    private final Integer value;
    private final String desc;

    FundStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
