package com.ruoyi.quartz.task;

import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.domain.FundHold;
import com.ruoyi.fund.domain.FundIncome;
import com.ruoyi.fund.service.*;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 处理每日收益数据
 * @author xiaoyang.li
 * @date 2019/12/5 12:06
 */
@Component("FundIncomeTask")
public class FundIncomeTask {
    @Autowired
    private IFundHoldService fundHoldService;
    @Autowired
    private IFundInfoHoldService fundInfoHoldService;
    @Autowired
    private IFundIncomeService fundIncomeService;
    @Autowired
    private IFundDaysNetWorthService fundDaysNetWorthService;

    /**
     * 定时任务
     * 处理每日收益数据
     */
    public void incomeMonyDataAdd(){
        //查询所有持有的基金 join info表查买入费率
        List<FundInfoHoldVo> fundInfoHolds = fundInfoHoldService.selectAll();
        fundInfoHolds.stream().forEach(fundInfoHold -> {
            String createBy = fundInfoHold.getCreateBy();
            String fundInfoCode = fundInfoHold.getFundInfoCode();
            //查询该基金最早的购买记录 , 最早的购买日期
            Optional<Integer> netWorkDate = Optional.ofNullable(fundHoldService.getEarliesDataByCode(fundInfoCode))
                    .map(FundHold::getNetWorkDate);

            //查询fundInfoHold中按创建人、基金代码 最晚的收益记录日期
            Optional<Integer> inComeNetWorkDate = Optional.ofNullable(fundIncomeService.selectLastDataByCreateAndCode(createBy, fundInfoCode))
                    .map(FundIncome::getNetWorkDate);
            //查询到最晚日期后替换最早购买日期，减少数据循环量，并防止重复数据生成
            if(inComeNetWorkDate.isPresent()){
                netWorkDate = inComeNetWorkDate;
            }
            netWorkDate.ifPresent(netWorkDateInt -> {
                //根据最早的购买日期 查询该日期后 该基金  的净值数据
                List<FundDaysNetWorth> fundDaysNetWorths = fundDaysNetWorthService.selectFundDaysNetWorthListAsc(
                        FundDaysNetWorth.builder().fundInfoCode(fundInfoCode).netWortDate(netWorkDateInt).build());
                //前一日净值
                AtomicReference<Double> lastNetValueUnit = new AtomicReference<>(null);
                //记录上一次的持有金额，当持有金额变化时，计算买入手续费
                AtomicReference<Double> lastMony = new AtomicReference<>(0d);
                //循环 每日数据生成收益记录
                fundDaysNetWorths.stream().forEach(fundDaysNetWorth -> {
                    if(lastNetValueUnit.get() != null){
                        //统计指定日期以前的数据 统计总份额和总金额
                        //select sum(trade_share) trade_share,sum(mony) mony from fund_hold where net_work_date < 191010 and fund_info_code ='110011' AND create_by = 'admin'
                        FundHold sumFundHold = fundHoldService.statisticalByDateInt(fundDaysNetWorth.getNetWortDate(), fundInfoCode, createBy);
                        Double sumTradeShare = sumFundHold.getTradeShare();
                        Double sumMony = sumFundHold.getMony();
                        //今日收益为 （当前净值-上日净值）*持有份额
                        Double incomeMony =(fundDaysNetWorth.getNetValueUnit() - lastNetValueUnit.get()) * sumTradeShare;
                        FundIncome saveData = new FundIncome();
                        saveData.setId(UUID.randomUUID().toString());
                        saveData.setNetWorkDate(fundDaysNetWorth.getNetWortDate());
                        saveData.setFundInfoCode(fundInfoCode);
                        saveData.setHoldMony(sumMony);
                        saveData.setHoldTradeShare(sumTradeShare);
                        saveData.setIncomeMony(incomeMony);
                        if(Objects.equals(lastMony.get() , 0d) || lastMony.get() < sumMony){
                            //基金购买手续费
                            double buyPoundage = (sumMony - lastMony.get()) * fundInfoHold.getBuyRate()/100;
                            saveData.setBuyPoundage(buyPoundage);
                        }
                        saveData.setCreateBy(createBy);
                        fundIncomeService.insertFundIncome(saveData);
                        lastMony.set(sumMony);
                    }
                    lastNetValueUnit.set(fundDaysNetWorth.getNetValueUnit());
                });
            });
        });
    }
}
