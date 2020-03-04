package com.ruoyi.fund.data;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.fund.data.enums.RequestUrl;
import com.ruoyi.fund.data.param.FundRankParam;
import com.ruoyi.fund.data.response.FundDetailResponse;
import com.ruoyi.fund.data.response.FundRankAndHoteResponse;
import com.ruoyi.common.utils.http.HttpUtils;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 9:41
 */
public class FundData {

    /**
     * 获取基金排行
     * @param fundRankParam
     * @return
     */
    public static FundRankAndHoteResponse postV1FundRank(FundRankParam fundRankParam) {
        String url = RequestUrl.PostV1FundRank.getValue();
        String result = HttpUtils.sendPost(url, fundRankParam);
        return JSONObject.parseObject(result , FundRankAndHoteResponse.class);
    }


    /**
     * 获取基金详情
     * @param code
     * @return
     */
    public static FundDetailResponse getV1FundDetail(String code) {
        String url = RequestUrl.GetV1FundDetail.getValue();
        String result = HttpUtils.sendGet(url, "code=" + code);
        return JSONObject.parseObject(result , FundDetailResponse.class);
    }

    /**
     * 获取基金热门数据
     * @return
     */
    public static FundRankAndHoteResponse getV1FundHot() {
        String url = RequestUrl.GetV1FundHot.getValue();
        String result = HttpUtils.sendGet(url, "");
        return JSONObject.parseObject(result , FundRankAndHoteResponse.class);
    }

}
