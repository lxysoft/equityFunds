package com.ruoyi.fund.service;

import com.ruoyi.common.core.domain.AjaxResult;

/**
 * @author xiaoyang.li
 * @date 2019/12/2 12:01
 */
public interface FundBaseService {
    /**
     * 查询mongdb中数据根据 基金代码
     * @param fundCode
     * @return
     */
    AjaxResult findByCode(String fundCode);
    /**
     * 查询mongdb中数据根据 基金代码
     * @param fundCode
     * @return
     */
    String findByCodeStr(String fundInfoCode);
}
