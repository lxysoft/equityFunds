package com.ruoyi.fund.data.param;

import lombok.Data;

import java.util.List;

/**
 * @author xiaoyang.li
 * @date 2019/11/29 10:06
 * 获取基金排行 请求参数
 */
@Data
public class FundRankParam {
    /**
     *基金种类(可以接受多个参数)
     * 默认值: 所有类型
     * FundType 枚举
     */
    private List<String> fundType;
    /**
     *排序方式：lastWeek最近一周,lastMonth最近一个月涨幅排序
     * 默认值: thisYear
     * Sort 枚举
     */
    private String sort;
    /**
     *基金公司,选择"易方达"就只返回易方达的基金(接受多个参数)
     * 默认值: 所有基金公司
     * FundCompany 枚举
     */
    private List<String> fundCompany;
    /**
     *基金认证等级,也支持一,二,三,四星(非必需)
     * 默认值: 无认证等级要求
     * FundGrade 枚举
     */
    private List<String> fundGrade;
    /**
     *基金成立时间限制1:小于一年》2:小于两年.依次类推(非必需)
     * 默认值: 1
     */
    private Integer creatTimeLimit;
    /**
     * 基金规模单位亿,10表示10亿以下,100表示100亿以下,1001表示100亿以上(非必需)
     * 允许值: [10, 100, 1001]
     */
    private Integer fundScale;
    /**
     * 排序方式0:降序:1升序
     * 默认值: 0
     * 允许值: {0，1}
     */
    private Integer asc;

    /**
     *页码
     * 默认值: 1
     */
    private Integer pageIndex;
    /**
     *每页显示的数量
     * 默认值: 10
     */
    private Integer pageSize;

}
