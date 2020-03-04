package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.PatternUtils;
import com.ruoyi.fund.domain.FundDaysNetWorth;
import com.ruoyi.fund.domain.FundHold;
import com.ruoyi.fund.domain.FundInfoHold;
import com.ruoyi.fund.domain.FundShareBonus;
import com.ruoyi.fund.service.IFundDaysNetWorthService;
import com.ruoyi.fund.service.IFundHoldService;
import com.ruoyi.fund.service.IFundInfoHoldService;
import com.ruoyi.fund.service.IFundShareBonusService;
import com.ruoyi.fund.vo.FundHoldVo;
import com.ruoyi.fund.vo.FundInfoHoldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 处理分红数据与昨日净值
 * @author xiaoyang.li
 * @date 2019/12/5 12:06
 */
@Component("fundShareBonusTask")
public class FundShareBonusTask {
    @Autowired
    private IFundHoldService fundHoldService;
    @Autowired
    private IFundInfoHoldService fundInfoHoldService;
    @Autowired
    private IFundShareBonusService fundShareBonusService;
    @Autowired
    private IFundDaysNetWorthService fundDaysNetWorthService;

    /**
     * 定时任务
     * 处理分红数据
     */
    public void shareBonusAdd(){
        //查询持有基金列表
        List<FundInfoHoldVo> fundInfoHolds = fundInfoHoldService.selectAll(null);
        fundInfoHolds.stream().forEach(fundInfoHold -> {
            //查询每日估值中有分红的数据
            List<FundDaysNetWorth> FundDaysNetWorths = fundDaysNetWorthService.findHaveShareBonusData(
                    null, null, fundInfoHold.getFundInfoCode());
            FundDaysNetWorths.parallelStream().forEach(fundDaysNetWorth -> {
                //查询分红记录中该用户、该日期、该基金是否有过分红记录
                FundShareBonus shareBonus = new FundShareBonus();
                shareBonus.setCreateBy(fundInfoHold.getCreateBy());
                shareBonus.setShareBonusDate(fundDaysNetWorth.getNetWortDate());
                shareBonus.setFundInfoCode(fundInfoHold.getFundInfoCode());
                List<FundShareBonus> fundShareBonuses = fundShareBonusService.selectAll(shareBonus);
                //同日期同用户同基金没有生成分红记录
                if(fundShareBonuses.size() == 0){
                    //查询所有符合条件的购买记录
                    FundHold fundHold = new FundHold();
                    fundHold.setFundInfoCode(fundInfoHold.getFundInfoCode());
                    fundHold.setNetWorkDate(fundDaysNetWorth.getNetWortDate());
                    List<FundHoldVo> fundHolds = fundHoldService.selectAll(fundHold);

                    //统计分红为现金的
                    double cash = fundHolds.stream()
                            .filter(hold -> Objects.equals(hold.getShareBonusType(), "1"))
                            .mapToDouble(FundHold::getTradeShare).sum();
                    if(cash > 0){
                        shareBonusAddAndFormate(fundDaysNetWorth ,fundInfoHold.getCreateBy(),
                                "1" , cash);
                    }
                    //统计分红为在投资的
                    double again = fundHolds.stream()
                            .filter(hold -> Objects.equals(hold.getShareBonusType(), "2"))
                            .mapToDouble(FundHold::getTradeShare).sum();
                    if(again > 0){
                        shareBonusAddAndFormate(fundDaysNetWorth ,fundInfoHold.getCreateBy(),
                                "2" , again);
                    }

                }
            });
        });

    }


    /**
     * 定时任务中处理分红数据
     *  处理红利数据记录
     * @param fundDaysNetWorth
     * @param loginName
     * @param shareBonusType
     * @param tradeShare
     */
    private void shareBonusAddAndFormate(FundDaysNetWorth fundDaysNetWorth,
                    String loginName, String shareBonusType ,Double tradeShare) {
        String fundInfoCode = fundDaysNetWorth.getFundInfoCode();
        Integer netWortDate = fundDaysNetWorth.getNetWortDate();
        FundShareBonus shareBonus = new FundShareBonus();
        shareBonus.setFundInfoCode(fundInfoCode);
        shareBonus.setId(UUID.randomUUID().toString());
        shareBonus.setShareBonusDate(netWortDate);
        shareBonus.setCreateTime(new Date());
        shareBonus.setCreateBy(loginName);
        shareBonus.setShareBonusType(shareBonusType);
        shareBonus.setTradeShare(tradeShare);
        Double shareBonusOneMoney = PatternUtils.getStrNum(fundDaysNetWorth.getEachShareBonus());
        shareBonus.setShareBonusOneMoney(shareBonusOneMoney);
        //分红总数为单份金额*份数
        double shareBonusMoney = tradeShare * shareBonusOneMoney;
        shareBonus.setShareBonusMoney(shareBonusMoney);
        fundShareBonusService.insertFundShareBonus(shareBonus);
        //红利再投资的情况
        if(Objects.equals(shareBonusType , "2")){
            //计算分红日以前所有的投资记录统计总
            FundHold fundHold = new FundHold();
            fundHold.setId(UUID.randomUUID().toString());
            fundHold.setFundInfoCode(fundInfoCode);
            fundHold.setMony(shareBonusMoney);
            fundHold.setNetValueUnit(fundDaysNetWorth.getNetValueUnit());
            fundHold.setNetWorkDate(fundDaysNetWorth.getNetWortDate());
            fundHold.setTradeShare(shareBonusMoney / fundDaysNetWorth.getNetValueUnit());
            fundHold.setCreateBy(loginName);
            fundHold.setCreateTime(new Date());
            //用于标记是分红再投入份额
            fundHold.setFundHoldId("1");
            fundHoldService.insertFundHold(fundHold);
        }
    }
}
