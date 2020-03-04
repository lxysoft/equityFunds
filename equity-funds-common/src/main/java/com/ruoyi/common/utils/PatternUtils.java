package com.ruoyi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoyang.li
 * @date 2019/12/2 16:07
 */
public class PatternUtils {

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 在字符串中提取金额数
     * @param str
     * @return
     */
    public static Double getStrNum(String str) {
        String regxp = "((-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?)";
        Pattern p = Pattern.compile(regxp);
        Matcher m = p.matcher(str);
        while (m.find()) {
            String pStr = m.group();
            return Double.valueOf(pStr);
        }
        return 0d;
    }
}
