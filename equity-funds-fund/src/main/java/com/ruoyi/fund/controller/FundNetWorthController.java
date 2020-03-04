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
import com.ruoyi.fund.domain.FundNetWorth;
import com.ruoyi.fund.service.IFundNetWorthService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基金费率以及净值数据Controller
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/fund/netWorth")
public class FundNetWorthController extends BaseController
{
    private String prefix = "fund/netWorth";

    @Autowired
    private IFundNetWorthService fundNetWorthService;

    @RequiresPermissions("fund:netWorth:view")
    @GetMapping()
    public String netWorth()
    {
        return prefix + "/netWorth";
    }

    /**
     * 查询基金费率以及净值数据列表
     */
    @RequiresPermissions("fund:netWorth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundNetWorth fundNetWorth)
    {
        startPage();
        List<FundNetWorth> list = fundNetWorthService.selectFundNetWorthList(fundNetWorth);
        return getDataTable(list);
    }

    /**
     * 导出基金费率以及净值数据列表
     */
    @RequiresPermissions("fund:netWorth:export")
    @Log(title = "基金费率以及净值数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundNetWorth fundNetWorth)
    {
        List<FundNetWorth> list = fundNetWorthService.selectFundNetWorthList(fundNetWorth);
        ExcelUtil<FundNetWorth> util = new ExcelUtil<FundNetWorth>(FundNetWorth.class);
        return util.exportExcel(list, "netWorth");
    }

    /**
     * 新增基金费率以及净值数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存基金费率以及净值数据
     */
    @RequiresPermissions("fund:netWorth:add")
    @Log(title = "基金费率以及净值数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundNetWorth fundNetWorth)
    {
        return toAjax(fundNetWorthService.insertFundNetWorth(fundNetWorth));
    }

    /**
     * 修改基金费率以及净值数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FundNetWorth fundNetWorth = fundNetWorthService.selectFundNetWorthById(id);
        mmap.put("fundNetWorth", fundNetWorth);
        return prefix + "/edit";
    }

    /**
     * 修改保存基金费率以及净值数据
     */
    @RequiresPermissions("fund:netWorth:edit")
    @Log(title = "基金费率以及净值数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundNetWorth fundNetWorth)
    {
        return toAjax(fundNetWorthService.updateFundNetWorth(fundNetWorth));
    }

    /**
     * 删除基金费率以及净值数据
     */
    @RequiresPermissions("fund:netWorth:remove")
    @Log(title = "基金费率以及净值数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundNetWorthService.deleteFundNetWorthByIds(ids));
    }
}
