package com.ruoyi.fund.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundNetWorthMapper;
import com.ruoyi.fund.domain.FundNetWorth;
import com.ruoyi.fund.service.IFundNetWorthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基金费率以及净值数据Service业务层处理
 * 
 * @author lxy
 * @date 2019-11-29
 */
@Service
public class FundNetWorthServiceImpl implements IFundNetWorthService 
{
    @Autowired
    private FundNetWorthMapper fundNetWorthMapper;

    /**
     * 查询基金费率以及净值数据
     * 
     * @param id 基金费率以及净值数据ID
     * @return 基金费率以及净值数据
     */
    @Override
    public FundNetWorth selectFundNetWorthById(String id)
    {
        return fundNetWorthMapper.selectFundNetWorthById(id);
    }

    /**
     * 查询基金费率以及净值数据列表
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 基金费率以及净值数据
     */
    @Override
    public List<FundNetWorth> selectFundNetWorthList(FundNetWorth fundNetWorth)
    {
        return fundNetWorthMapper.selectFundNetWorthList(fundNetWorth);
    }

    /**
     * 新增基金费率以及净值数据
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 结果
     */
    @Override
    public int insertFundNetWorth(FundNetWorth fundNetWorth)
    {
        return fundNetWorthMapper.insertFundNetWorth(fundNetWorth);
    }

    /**
     * 修改基金费率以及净值数据
     * 
     * @param fundNetWorth 基金费率以及净值数据
     * @return 结果
     */
    @Override
    public int updateFundNetWorth(FundNetWorth fundNetWorth)
    {
        return fundNetWorthMapper.updateFundNetWorth(fundNetWorth);
    }

    /**
     * 删除基金费率以及净值数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundNetWorthByIds(String ids)
    {
        return fundNetWorthMapper.deleteFundNetWorthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基金费率以及净值数据信息
     * 
     * @param id 基金费率以及净值数据ID
     * @return 结果
     */
    @Override
    public int deleteFundNetWorthById(String id)
    {
        return fundNetWorthMapper.deleteFundNetWorthById(id);
    }
}
