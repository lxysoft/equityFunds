package com.ruoyi.fund.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.fund.data.FundData;
import com.ruoyi.fund.data.enums.FundStatus;
import com.ruoyi.fund.data.response.DetailData;
import com.ruoyi.fund.data.response.FundDetailResponse;
import com.ruoyi.fund.service.FundBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author xiaoyang.li
 * @date 2019/12/2 12:01
 */
@Service
public class FundBaseServiceImpl implements FundBaseService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String findByCodeStr(String fundCode) {
        if(fundCode.length() == 6) {
            Query query = new Query();
            query.addCriteria(Criteria.where("code").is(fundCode));
            DetailData detailData = mongoTemplate.findOne(query, DetailData.class);
            if (detailData == null) {
                FundDetailResponse v1FundDetail = FundData.getV1FundDetail(fundCode);
                if (!Objects.equals(v1FundDetail.getCode(), FundStatus.SUCCESS_200.getValue())) {
                    return "";
                }
                detailData = v1FundDetail.getData();
                mongoTemplate.insert(detailData);
            }
            return detailData.getCode() + "-" + detailData.getName();
        }
        return "";
    }

    @Override
    public AjaxResult findByCode(String fundCode) {
        AjaxResult ajax = new AjaxResult();
        if(fundCode.length() == 6){
            Query query = new Query();
            query.addCriteria(Criteria.where("code").is(fundCode));
            DetailData detailData = mongoTemplate.findOne(query, DetailData.class);
            if(detailData == null){
                FundDetailResponse v1FundDetail = FundData.getV1FundDetail(fundCode);
                if(!Objects.equals(v1FundDetail.getCode() , FundStatus.SUCCESS_200.getValue())){
                    String message = FundStatus.valueOf(String.valueOf(v1FundDetail.getCode())).getDesc();
                    return AjaxResult.error(message);
                }
                detailData = v1FundDetail.getData();
                mongoTemplate.insert(detailData);
            }

            ajax.put("value", new String[]{detailData.getCode() + "-" + detailData.getName()});
            return ajax;
        }
        ajax.put("value", new String[]{fundCode});
        return ajax;
    }
}
