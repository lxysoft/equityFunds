package com.ruoyi.fund.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.fund.service.FundBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author xiaoyang.li
 * @date 2019/12/2 10:47
 */
@Controller
@RequestMapping("/fund/base")
public class FundBaseController extends BaseController {

    @Autowired
    private FundBaseService fundBaseService;

    /**
     * 查询持有基金信息列表
     * @return
     */
    @GetMapping("/findByCode")
    @ResponseBody
    public AjaxResult findByCode(String fundCode)
    {
        return fundBaseService.findByCode(fundCode);
    }
}
