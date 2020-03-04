package com.ruoyi.fund.data.response;

import lombok.Data;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:21
 *  获取基金详情 返回结果
 */
@Data
public class FundDetailResponse {
    /**
     * 400
     * 解析请求失败
     *
     * 405
     * 无效的基金代码
     *
     * 500
     * 内部网络异常
     */
    private Integer code;
    private String message;
    /**
     * 基金详情数据
     */
    private DetailData data;
    /**
     * 基金代码
     */
    private String meta;

}