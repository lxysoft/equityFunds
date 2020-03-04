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
import com.ruoyi.fund.domain.FundShareBonus;
import com.ruoyi.fund.service.IFundShareBonusService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基金分红记录Controller
 * 
 * @author lxy
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/fund/shareBonus")
public class FundShareBonusController extends BaseController
{
    private String prefix = "fund/shareBonus";

    @Autowired
    private IFundShareBonusService fundShareBonusService;

    @RequiresPermissions("fund:shareBonus:view")
    @GetMapping()
    public String shareBonus()
    {
        return prefix + "/shareBonus";
    }

    /**
     * 查询基金分红记录列表
     */
    @RequiresPermissions("fund:shareBonus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundShareBonus fundShareBonus)
    {
        startPage();
        List<FundShareBonus> list = fundShareBonusService.selectFundShareBonusList(fundShareBonus);
        return getDataTable(list);
    }

    /**
     * 导出基金分红记录列表
     */
    @RequiresPermissions("fund:shareBonus:export")
    @Log(title = "基金分红记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundShareBonus fundShareBonus)
    {
        List<FundShareBonus> list = fundShareBonusService.selectFundShareBonusList(fundShareBonus);
        ExcelUtil<FundShareBonus> util = new ExcelUtil<FundShareBonus>(FundShareBonus.class);
        return util.exportExcel(list, "shareBonus");
    }

    /**
     * 新增基金分红记录
     */
    /*@GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }*/

    /**
     * 新增保存基金分红记录
     */
    /*@RequiresPermissions("fund:shareBonus:add")
    @Log(title = "基金分红记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundShareBonus fundShareBonus)
    {
        return toAjax(fundShareBonusService.insertFundShareBonus(fundShareBonus));
    }*/

    /**
     * 修改基金分红记录
     */
    /*@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FundShareBonus fundShareBonus = fundShareBonusService.selectFundShareBonusById(id);
        mmap.put("fundShareBonus", fundShareBonus);
        return prefix + "/edit";
    }*/

    /**
     * 修改保存基金分红记录
     */
    /*@RequiresPermissions("fund:shareBonus:edit")
    @Log(title = "基金分红记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundShareBonus fundShareBonus)
    {
        return toAjax(fundShareBonusService.updateFundShareBonus(fundShareBonus));
    }*/

    /**
     * 删除基金分红记录
     */
    /*@RequiresPermissions("fund:shareBonus:remove")
    @Log(title = "基金分红记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundShareBonusService.deleteFundShareBonusByIds(ids));
    }*/
}
