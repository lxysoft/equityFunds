package com.ruoyi.fund.service.impl;

import java.util.List;
import java.util.Objects;

import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundShareBonusMapper;
import com.ruoyi.fund.domain.FundShareBonus;
import com.ruoyi.fund.service.IFundShareBonusService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基金分红记录Service业务层处理
 * 
 * @author lxy
 * @date 2019-12-04
 */
@Service
public class FundShareBonusServiceImpl implements IFundShareBonusService 
{
    @Autowired
    private FundShareBonusMapper fundShareBonusMapper;

    /**
     * 查询基金分红记录
     * 
     * @param id 基金分红记录ID
     * @return 基金分红记录
     */
    @Override
    public FundShareBonus selectFundShareBonusById(Long id)
    {
        return fundShareBonusMapper.selectFundShareBonusById(id);
    }

    /**
     * 查询基金分红记录列表
     * 
     * @param fundShareBonus 基金分红记录
     * @return 基金分红记录
     */
    @Override
    public List<FundShareBonus> selectFundShareBonusList(FundShareBonus fundShareBonus)
    {
        fundShareBonus.setCreateBy(ShiroUtils.getLoginName());
        List<FundShareBonus> fundShareBonuses = fundShareBonusMapper.selectFundShareBonusList(fundShareBonus);
        fundShareBonuses.stream().forEach(data ->{
            data.setShareBonusType("红利再投资");
            if(Objects.equals(data.getShareBonusType() , "1")){
                data.setShareBonusType("红利变现");
            }
            data.setTradeShare(Arith.round(data.getTradeShare() , 2));
        });
        return fundShareBonuses;
    }

    @Override
    public List<FundShareBonus> selectAll(FundShareBonus fundShareBonus)
    {
        return fundShareBonusMapper.selectFundShareBonusList(fundShareBonus);
    }

    /**
     * 新增基金分红记录
     * 
     * @param fundShareBonus 基金分红记录
     * @return 结果
     */
    @Override
    public int insertFundShareBonus(FundShareBonus fundShareBonus)
    {
        return fundShareBonusMapper.insertFundShareBonus(fundShareBonus);
    }

    /**
     * 修改基金分红记录
     * 
     * @param fundShareBonus 基金分红记录
     * @return 结果
     */
    @Override
    public int updateFundShareBonus(FundShareBonus fundShareBonus)
    {
        fundShareBonus.setUpdateTime(DateUtils.getNowDate());
        return fundShareBonusMapper.updateFundShareBonus(fundShareBonus);
    }

    /**
     * 删除基金分红记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundShareBonusByIds(String ids)
    {
        return fundShareBonusMapper.deleteFundShareBonusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基金分红记录信息
     * 
     * @param id 基金分红记录ID
     * @return 结果
     */
    @Override
    public int deleteFundShareBonusById(Long id)
    {
        return fundShareBonusMapper.deleteFundShareBonusById(id);
    }
}
