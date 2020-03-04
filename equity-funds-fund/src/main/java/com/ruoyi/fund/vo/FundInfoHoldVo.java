package com.ruoyi.fund.vo;

import com.ruoyi.fund.domain.FundInfoHold;
import lombok.Data;

/**
 * @author xiaoyang.li
 * @date 2019/12/9 17:01
 */
@Data
public class FundInfoHoldVo extends FundInfoHold {
    /**
     * 基金买入费率
     */
    private Double buyRate;
}
