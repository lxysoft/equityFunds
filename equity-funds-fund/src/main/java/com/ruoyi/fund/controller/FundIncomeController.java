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
import com.ruoyi.fund.domain.FundIncome;
import com.ruoyi.fund.service.IFundIncomeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基金历史收益Controller
 * 
 * @author lxy
 * @date 2019-12-06
 */
@Controller
@RequestMapping("/fund/income")
public class FundIncomeController extends BaseController
{
    private String prefix = "fund/income";

    @Autowired
    private IFundIncomeService fundIncomeService;

    @RequiresPermissions("fund:income:view")
    @GetMapping()
    public String income()
    {
        return prefix + "/income";
    }

    /**
     * 查询基金历史收益列表
     */
    @RequiresPermissions("fund:income:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundIncome fundIncome)
    {
        startPage();
        List<FundIncome> list = fundIncomeService.selectFundIncomeList(fundIncome);
        return getDataTable(list);
    }

    /**
     * 导出基金历史收益列表
     */
    @RequiresPermissions("fund:income:export")
    @Log(title = "基金历史收益", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundIncome fundIncome)
    {
        List<FundIncome> list = fundIncomeService.selectFundIncomeList(fundIncome);
        ExcelUtil<FundIncome> util = new ExcelUtil<FundIncome>(FundIncome.class);
        return util.exportExcel(list, "income");
    }

    /**
     * 新增基金历史收益
     */
    /*@GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }*/

    /**
     * 新增保存基金历史收益
     */
    /*@RequiresPermissions("fund:income:add")
    @Log(title = "基金历史收益", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundIncome fundIncome)
    {
        return toAjax(fundIncomeService.insertFundIncome(fundIncome));
    }
*/
    /**
     * 修改基金历史收益
     */
    /*@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FundIncome fundIncome = fundIncomeService.selectFundIncomeById(id);
        mmap.put("fundIncome", fundIncome);
        return prefix + "/edit";
    }*/

    /**
     * 修改保存基金历史收益
     */
    /*@RequiresPermissions("fund:income:edit")
    @Log(title = "基金历史收益", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundIncome fundIncome)
    {
        return toAjax(fundIncomeService.updateFundIncome(fundIncome));
    }
*/
    /**
     * 删除基金历史收益
     */
    /*@RequiresPermissions("fund:income:remove")
    @Log(title = "基金历史收益", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundIncomeService.deleteFundIncomeByIds(ids));
    }*/
}
