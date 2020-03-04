package com.ruoyi.fund.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.fund.service.FundBaseService;
import com.ruoyi.fund.vo.FundHoldVo;
import com.ruoyi.system.domain.SysUser;
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
import com.ruoyi.fund.domain.FundHold;
import com.ruoyi.fund.service.IFundHoldService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 持有基金信息Controller
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/fund/hold")
public class FundHoldController extends BaseController
{
    private String prefix = "fund/hold";

    @Autowired
    private IFundHoldService fundHoldService;
    @Autowired
    private FundBaseService fundBaseService;

    @RequiresPermissions("fund:hold:view")
    @GetMapping()
    public String hold()
    {
        return prefix + "/hold";
    }

    /**
     * 查询持有基金信息列表
     */
    @RequiresPermissions("fund:hold:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FundHold fundHold)
    {
        startPage();
        List<FundHoldVo> list = fundHoldService.selectFundHoldList(fundHold);
        return getDataTable(list);
    }

    /**
     * 导出持有基金信息列表
     */
    @RequiresPermissions("fund:hold:export")
    @Log(title = "持有基金信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FundHold fundHold)
    {
        List<FundHoldVo> list = fundHoldService.selectFundHoldList(fundHold);
        ExcelUtil<FundHoldVo> util = new ExcelUtil<>(FundHoldVo.class);
        return util.exportExcel(list, "hold");
    }

    /**
     * 新增持有基金信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存持有基金信息
     */
    @RequiresPermissions("fund:hold:add")
    @Log(title = "持有基金信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FundHoldVo fundHold)
    {

        return fundHoldService.insertAndEditFundHold(fundHold);
    }

    /**
     * 修改持有基金信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FundHoldVo fundHold = fundHoldService.selectFundHoldById(id);
        fundHold.setFundInfoCode(fundBaseService.findByCodeStr(fundHold.getFundInfoCode()));

        mmap.put("fundHold", fundHold);
        return prefix + "/edit";
    }

    /**
     * 修改保存持有基金信息
     */
    @RequiresPermissions("fund:hold:edit")
    @Log(title = "持有基金信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FundHoldVo fundHold)
    {
        return fundHoldService.insertAndEditFundHold(fundHold);
    }

    /**
     * 删除持有基金信息
     */
    /*@RequiresPermissions("fund:hold:remove")
    @Log(title = "持有基金信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fundHoldService.deleteFundHoldByIds(ids));
    }*/
}
