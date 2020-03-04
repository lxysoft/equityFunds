package com.ruoyi.fund.controller;

import java.util.List;

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
import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.service.IFundInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基金基础数据Controller
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/fund/info")
public class FundInfoController extends BaseController
{
    private String prefix = "fund/info";

    @Autowired
    private IFundInfoService fundInfoService;

    @RequiresPermissions("fund:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询基金基础数据列表
     */
    @RequiresPermissions("fund:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundInfo fundInfo)
    {
        startPage();
        List<FundInfoVo> list = fundInfoService.selectFundInfoList(fundInfo);
        return getDataTable(list);
    }

    /**
     * 导出基金基础数据列表
     */
    @RequiresPermissions("fund:info:export")
    @Log(title = "基金基础数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundInfo fundInfo)
    {
        List<FundInfoVo> list = fundInfoService.selectFundInfoList(fundInfo);
        ExcelUtil<FundInfoVo> util = new ExcelUtil<>(FundInfoVo.class);
        return util.exportExcel(list, "info");
    }

   /* *//**
     * 新增基金基础数据
     *//*
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    *//**
     * 新增保存基金基础数据
     *//*
    @RequiresPermissions("fund:info:add")
    @Log(title = "基金基础数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundInfo fundInfo)
    {
        return toAjax(fundInfoService.insertFundInfo(fundInfo));
    }

    *//**
     * 修改基金基础数据
     *//*
    @GetMapping("/edit/{code}")
    public String edit(@PathVariable("code") String code, ModelMap mmap)
    {
        FundInfo fundInfo = fundInfoService.selectFundInfoById(code);
        mmap.put("fundInfo", fundInfo);
        return prefix + "/edit";
    }

    *//**
     * 修改保存基金基础数据
     *//*
    @RequiresPermissions("fund:info:edit")
    @Log(title = "基金基础数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundInfo fundInfo)
    {
        return toAjax(fundInfoService.updateFundInfo(fundInfo));
    }

    *//**
     * 删除基金基础数据
     *//*
    @RequiresPermissions("fund:info:remove")
    @Log(title = "基金基础数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundInfoService.deleteFundInfoByIds(ids));
    }*/
}
