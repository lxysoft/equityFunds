package com.ruoyi.fund.data.response;

import lombok.Data;

import java.util.List;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:10
 *
 */
@Data
public class FundRankAndHoteResponse {
    /**
     * 202
     * 没有符合条件的基金
     *
     * 400
     * 解析请求失败,一般是参数错误
     *
     * 500
     * 内部网络异常
     */
    private Integer code;
    /**
     * 操作提示
     */
    private String message;
    private List<RankAndHotData> data;
    private MetaResponse meta;

}
