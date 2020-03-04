package com.ruoyi.fund.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.fund.vo.FundInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundInfoMapper;
import com.ruoyi.fund.domain.FundInfo;
import com.ruoyi.fund.service.IFundInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基金基础数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-11-29
 */
@Service
public class FundInfoServiceImpl implements IFundInfoService 
{
    @Autowired
    private FundInfoMapper fundInfoMapper;

    /**
     * 查询基金基础数据
     * 
     * @param code 基金基础数据ID
     * @return 基金基础数据
     */
    @Override
    public FundInfo selectFundInfoById(String code)
    {
        return fundInfoMapper.selectFundInfoById(code);
    }

    /**
     * 查询基金基础数据列表
     * 
     * @param fundInfo 基金基础数据
     * @return 基金基础数据
     */
    @Override
    public List<FundInfoVo> selectFundInfoList(FundInfo fundInfo)
    {
        return fundInfoMapper.selectFundInfoList(fundInfo);
    }

    /**
     * 新增基金基础数据
     * 
     * @param fundInfo 基金基础数据
     * @return 结果
     */
    @Override
    public int insertFundInfo(FundInfo fundInfo)
    {
        fundInfo.setCreateTime(DateUtils.getNowDate());
        return fundInfoMapper.insertFundInfo(fundInfo);
    }

    /**
     * 修改基金基础数据
     * 
     * @param fundInfo 基金基础数据
     * @return 结果
     */
    @Override
    public int updateFundInfo(FundInfo fundInfo)
    {
        fundInfo.setUpdateTime(DateUtils.getNowDate());
        return fundInfoMapper.updateFundInfo(fundInfo);
    }

    /**
     * 删除基金基础数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundInfoByIds(String ids)
    {
        return fundInfoMapper.deleteFundInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基金基础数据信息
     * 
     * @param code 基金基础数据ID
     * @return 结果
     */
    @Override
    public int deleteFundInfoById(String code)
    {
        return fundInfoMapper.deleteFundInfoById(code);
    }
}
