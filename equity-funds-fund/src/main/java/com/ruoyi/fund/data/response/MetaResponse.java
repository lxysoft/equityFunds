package com.ruoyi.fund.data.response;

import lombok.Data;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:11
 */
@Data
public class MetaResponse {
    /**
     *页码
     */
    private Integer pageIndex;
    /**
     * 每页的数量
     */
    private Integer pageSize;
}
