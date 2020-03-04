package com.ruoyi.fund.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.service.IFundDaysNetWorthService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录基金每日净值数据Controller
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/fund/worth")
public class FundDaysNetWorthController extends BaseController
{
    private String prefix = "fund/worth";

    @Autowired
    private IFundDaysNetWorthService fundDaysNetWorthService;

    @RequiresPermissions("fund:worth:view")
    @GetMapping()
    public String worth()
    {
        return prefix + "/worth";
    }

    /**
     * 查询记录基金每日净值数据列表
     */
    @RequiresPermissions("fund:worth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundDaysNetWorth fundDaysNetWorth)
    {
        startPage();
        List<FundDaysNetWorth> list = fundDaysNetWorthService.selectFundDaysNetWorthList(fundDaysNetWorth);
        return getDataTable(list);
    }

    /**
     * 导出记录基金每日净值数据列表
     */
    @RequiresPermissions("fund:worth:export")
    @Log(title = "记录基金每日净值数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundDaysNetWorth fundDaysNetWorth)
    {
        List<FundDaysNetWorth> list = fundDaysNetWorthService.selectFundDaysNetWorthList(fundDaysNetWorth);
        ExcelUtil<FundDaysNetWorth> util = new ExcelUtil<FundDaysNetWorth>(FundDaysNetWorth.class);
        return util.exportExcel(list, "worth");
    }

    /**
     * 新增记录基金每日净值数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存记录基金每日净值数据
     */
    @RequiresPermissions("fund:worth:add")
    @Log(title = "记录基金每日净值数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundDaysNetWorth fundDaysNetWorth)
    {
        return toAjax(fundDaysNetWorthService.insertFundDaysNetWorth(fundDaysNetWorth));
    }

    /**
     * 修改记录基金每日净值数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FundDaysNetWorth fundDaysNetWorth = fundDaysNetWorthService.selectFundDaysNetWorthById(id);
        mmap.put("fundDaysNetWorth", fundDaysNetWorth);
        return prefix + "/edit";
    }

    /**
     * 修改保存记录基金每日净值数据
     */
    @RequiresPermissions("fund:worth:edit")
    @Log(title = "记录基金每日净值数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundDaysNetWorth fundDaysNetWorth)
    {
        return toAjax(fundDaysNetWorthService.updateFundDaysNetWorth(fundDaysNetWorth));
    }

    /**
     * 删除记录基金每日净值数据
     */
    @RequiresPermissions("fund:worth:remove")
    @Log(title = "记录基金每日净值数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundDaysNetWorthService.deleteFundDaysNetWorthByIds(ids));
    }
}
