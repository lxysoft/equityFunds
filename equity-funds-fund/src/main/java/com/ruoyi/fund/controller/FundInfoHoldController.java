package com.ruoyi.fund.controller;

import java.util.List;

import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import com.ruoyi.fund.vo.FundInfoVo;
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
import com.ruoyi.fund.domain.FundInfoHold;
import com.ruoyi.fund.service.IFundInfoHoldService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 持有基金信息关联表Controller
 * 
 * @author ruoyi
 * @date 2019-12-03
 */
@Controller
@RequestMapping("/fund/infohold")
public class FundInfoHoldController extends BaseController
{
    private String prefix = "fund/infohold";

    @Autowired
    private IFundInfoHoldService fundInfoHoldService;

    @RequiresPermissions("fund:infohold:view")
    @GetMapping()
    public String infohold()
    {
        return prefix + "/infohold";
    }

    /**
     * 查询持有基金信息关联表列表
     */
    @RequiresPermissions("fund:infohold:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundInfo fundInfo)
    {
        startPage();
        List<FundInfoVo> list = fundInfoHoldService.selectFundInfoHoldList(fundInfo);
        return getDataTable(list);
    }

    /**
     * 导出持有基金信息关联表列表
     */
    @RequiresPermissions("fund:infohold:export")
    @Log(title = "持有基金信息关联表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundInfo fundInfo)
    {
        List<FundInfoVo> list = fundInfoHoldService.selectFundInfoHoldList(fundInfo);
        ExcelUtil<FundInfoVo> util = new ExcelUtil<>(FundInfoVo.class);
        return util.exportExcel(list, "infohold");
    }

   /* *//**
     * 新增持有基金信息关联表
     *//*
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    *//**
     * 新增保存持有基金信息关联表
     *//*
    @RequiresPermissions("fund:infohold:add")
    @Log(title = "持有基金信息关联表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundInfoHold fundInfoHold)
    {
        return toAjax(fundInfoHoldService.insertFundInfoHold(fundInfoHold));
    }

    *//**
     * 修改持有基金信息关联表
     *//*
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FundInfoHold fundInfoHold = fundInfoHoldService.selectFundInfoHoldById(id);
        mmap.put("fundInfoHold", fundInfoHold);
        return prefix + "/edit";
    }

    *//**
     * 修改保存持有基金信息关联表
     *//*
    @RequiresPermissions("fund:infohold:edit")
    @Log(title = "持有基金信息关联表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundInfoHold fundInfoHold)
    {
        return toAjax(fundInfoHoldService.updateFundInfoHold(fundInfoHold));
    }

    *//**
     * 删除持有基金信息关联表
     *//*
    @RequiresPermissions("fund:infohold:remove")
    @Log(title = "持有基金信息关联表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundInfoHoldService.deleteFundInfoHoldByIds(ids));
    }*/
}
