package com.ruoyi.fund.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fund.mapper.FundIncomeMapper;
import com.ruoyi.fund.domain.FundIncome;
import com.ruoyi.fund.service.IFundIncomeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 基金历史收益Service业务层处理
 * 
 * @author lxy
 * @date 2019-12-06
 */
@Service
public class FundIncomeServiceImpl implements IFundIncomeService
{
    @Autowired
    private FundIncomeMapper fundIncomeMapper;

    /**
     * 查询基金历史收益
     * 
     * @param id 基金历史收益ID
     * @return 基金历史收益
     */
    @Override
    public FundIncome selectFundIncomeById(String id)
    {
        return fundIncomeMapper.selectFundIncomeById(id);
    }

    /**
     * 查询基金历史收益列表
     * 
     * @param fundIncome 基金历史收益
     * @return 基金历史收益
     */
    @Override
    public List<FundIncome> selectFundIncomeList(FundIncome fundIncome)
    {
        List<FundIncome> fundIncomes = fundIncomeMapper.selectFundIncomeList(fundIncome);
        fundIncomes.stream().forEach(income ->{
            income.setHoldMony(Arith.round(income.getHoldMony() , 2));
            income.setHoldTradeShare(Arith.round(income.getHoldTradeShare() , 2));
            income.setIncomeMony(Arith.round(income.getIncomeMony() , 2));
            try {
                income.setCreateTime(new SimpleDateFormat("yyyyMMdd").parse(getRelaYearDate(income.getNetWorkDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return fundIncomes;
    }

    /**
     * 将yyMMdd时间处理成yyyyMMDD
     * @param netWorkDateInt
     * @return
     */
    private String getRelaYearDate(Integer netWorkDateInt){
        String netWorkDate = String.valueOf(netWorkDateInt);
        int year = LocalDate.now().getYear();
        if(netWorkDate.length() == 5){
            return "200"+netWorkDate;
        }else{
            String calculateStr = "20"+netWorkDate;
            Integer oldYear = Integer.valueOf(calculateStr.substring(0, 4));
            if(year < oldYear){
                return "19"+netWorkDate;
            }else{
                return "20"+netWorkDate;
            }
        }
    }

    /**
     * 新增基金历史收益
     * 
     * @param fundIncome 基金历史收益
     * @return 结果
     */
    @Override
    public int insertFundIncome(FundIncome fundIncome)
    {
        fundIncome.setCreateTime(DateUtils.getNowDate());
        return fundIncomeMapper.insertFundIncome(fundIncome);
    }

    /**
     * 修改基金历史收益
     * 
     * @param fundIncome 基金历史收益
     * @return 结果
     */
    @Override
    public int updateFundIncome(FundIncome fundIncome)
    {
        return fundIncomeMapper.updateFundIncome(fundIncome);
    }

    /**
     * 删除基金历史收益对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFundIncomeByIds(String ids)
    {
        return fundIncomeMapper.deleteFundIncomeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除基金历史收益信息
     * 
     * @param id 基金历史收益ID
     * @return 结果
     */
    @Override
    public int deleteFundIncomeById(String id)
    {
        return fundIncomeMapper.deleteFundIncomeById(id);
    }

    /**
     * 查询fundInfoHold中按创建人、基金代码 最晚的收益记录日期
     * @param createBy
     * @param fundInfoCode
     * @return
     */
    @Override
    public FundIncome selectLastDataByCreateAndCode(String createBy, String fundInfoCode) {
        return fundIncomeMapper.selectLastDataByCreateAndCode(createBy , fundInfoCode);
    }
}
