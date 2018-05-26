package com.jiezh.pub.util;

import com.alibaba.druid.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/****
 * 验证工具
 * 
 * @ClassName: ValiDateUtil
 * 
 * @author 李志恒 E-mail: zhihengli@jiezhongchina.com *
 * 
 * @date 2016年3月1日15:42:37
 */
public class ValiDateUtil {
    /**
     * 验证身份证
     * 
     * @param idcard
     * @return
     */
    public static String checkIdcard(String idcard) {
        String[] Errors = new String[] {"验证通过!", "身份证号码位数不对!", "身份证号码出生日期超出范围或含有非法字符!", "身份证号码校验错误!", "身份证地区非法!"};
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(11, "北京");
        map.put(12, "天津");
        map.put(13, "河北");
        map.put(14, "山西");
        map.put(15, "内蒙古");
        map.put(21, "辽宁");
        map.put(22, "吉林");
        map.put(23, "黑龙江");
        map.put(31, "上海");
        map.put(32, "江苏");
        map.put(33, "浙江");
        map.put(34, "安徽");
        map.put(35, "福建");
        map.put(36, "江西");
        map.put(37, "山东");
        map.put(41, "河南");
        map.put(42, "湖北");
        map.put(43, "湖南");
        map.put(44, "广东");
        map.put(45, "广西");
        map.put(46, "海南");
        map.put(50, "重庆");
        map.put(51, "四川");
        map.put(52, "贵州");
        map.put(53, "云南");
        map.put(54, "西藏");
        map.put(61, "陕西");
        map.put(62, "甘肃");
        map.put(63, "青海");
        map.put(64, "宁夏");
        map.put(65, "新疆");
        map.put(71, "台湾");
        map.put(81, "香港");
        map.put(82, "澳门");
        map.put(91, "国外");
        String ereg, M, JYM;
        ;
        int Y;
        int S;
        char[] idcard_array = new char[] {};
        idcard_array = idcard.toCharArray();

        if (!idcard.matches("^\\d{15}||\\d{17}[0-9Xx]$")) {
            return Errors[2];
        }

        if (map.get(Integer.parseInt(idcard.substring(0, 2))) == null) return Errors[4];
        switch (idcard.length()) {
            case 15:
                if ((Integer.parseInt(idcard.substring(6, 8)) + 1900) % 4 == 0
                    || ((Integer.parseInt(idcard.substring(6, 8)) + 1900) % 100 == 0 && (Integer.parseInt(idcard.substring(6, 8)) + 1900) % 4 == 0)) {
                    ereg =
                        "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";// 测试出生日期的合法性
                } else {
                    ereg =
                        "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";// 测试出生日期的合法性
                }
                if (idcard.matches(ereg)) return Errors[0];
                else return Errors[2];

            case 18:
                if (Integer.parseInt(idcard.substring(6, 10)) % 4 == 0
                    || (Integer.parseInt(idcard.substring(6, 10)) % 100 == 0 && Integer.parseInt(idcard.substring(6, 10)) % 4 == 0)) {
                    ereg =
                        "^[1-9][0-9]{5}((19[0-9]{2})|(2[0-9]{3}))((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";// 闰年出生日期的合法性正则表达式
                } else {
                    ereg =
                        "^[1-9][0-9]{5}((19[0-9]{2})|(2[0-9]{3}))((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";// 平年出生日期的合法性正则表达式
                }
                if (idcard.matches(ereg)) {
                    if (idcard.substring(6, 7) == "2") {
                        String monthStr = null, dayStr = null;
                        Date newDate1 = new Date();
                        int m = newDate1.getMonth() + 1;
                        int d = newDate1.getDate();
                        int y = newDate1.getYear();
                        if (m < 10) {
                            monthStr = "0" + m;
                        } else {
                            monthStr = "" + m;
                        }
                        if (d < 10) {
                            dayStr = "0" + d;
                        } else {
                            dayStr = "" + d;
                        }
                        String yearStr = y + "";
                        String dateStr = yearStr + monthStr + dayStr;
                        if (Integer.parseInt(idcard.substring(6, 8)) > Integer.parseInt(dateStr)) return Errors[2];
                    }

                    S = (Character.getNumericValue(idcard_array[0]) + Character.getNumericValue(idcard_array[10])) * 7
                        + (Character.getNumericValue(idcard_array[1]) + Character.getNumericValue(idcard_array[11])) * 9
                        + (Character.getNumericValue(idcard_array[2]) + Character.getNumericValue(idcard_array[12])) * 10
                        + (Character.getNumericValue(idcard_array[3]) + Character.getNumericValue(idcard_array[13])) * 5
                        + (Character.getNumericValue(idcard_array[4]) + Character.getNumericValue(idcard_array[14])) * 8
                        + (Character.getNumericValue(idcard_array[5]) + Character.getNumericValue(idcard_array[15])) * 4
                        + (Character.getNumericValue(idcard_array[6]) + Character.getNumericValue(idcard_array[16])) * 2
                        + Character.getNumericValue(idcard_array[7]) * 1 + Character.getNumericValue(idcard_array[8]) * 6
                        + Character.getNumericValue(idcard_array[9]) * 3;
                    Y = S % 11;
                    M = "F";
                    JYM = "10X98765432";
                    M = JYM.substring(Y, Y + 1);
                    if (M.equalsIgnoreCase(Character.toString(idcard_array[17]))) return Errors[0];
                    else return Errors[3];
                } else return Errors[2];

            default:
                return Errors[1];

        }
    }

    /**
     * 根据身份证号获取出生日期
     * 
     * @param idcard
     * @return
     */
    public static String checkBirthday(String idcard) {
        String birthday = null;
        // 身份号码位数及格式检验
        switch (idcard.length()) {
            case 15:
                birthday = "19" + idcard.substring(6, 8) + "-" + idcard.substring(8, 10) + "-" + idcard.substring(10, 12);
                break;
            case 18:
                birthday = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
                break;
        }
        return birthday;
    }

    /**
     * 根据身份证获取性别
     *
     * @param idcard
     * @return
     */
    public static String checkSex(String idcard) {
        String sex = "";
        // 身份号码位数及格式检验
        switch (idcard.length()) {
            case 15:
                sex = Integer.parseInt(idcard.substring(14, 15)) % 2 == 0 ? "0" : "1";
                break;

            case 18:
                sex = Integer.parseInt(idcard.substring(16, 17)) % 2 == 0 ? "0" : "1";
                break;

        }
        return sex;
    }

    /**
     * 数值型数据的区间验证
     *
     * @author 钟林俊 E-mail：linjunzhong@jiezhongchina.com
     * @param min 最小值
     * @param max 最大值
     * @param integer 需要验证的数据（组）
     */
    public static void numberValidate(Integer min, Integer max, Integer ... integer) {
        for (Integer i: integer) {
            if(min != null){
                if(min.compareTo(i) == 1){
                    throw new RuntimeException("数据小于最低值：" + min);
                }
            }
            if(max != null){
                if(max.compareTo(i) == -1){
                    throw new RuntimeException("数据大于最大值：" + max);
                }
            }
        }
    }

    /**
     * 非空验证
     *
     * @author 钟林俊 E-mail：linjunzhong@jiezhongchina.com
     * @param requiredVal 需要被验证的数据（组）
     */
    public static  void requiredValidate(Object ... requiredVal) {
        String str;
        for (Object obj: requiredVal) {
            str = String.valueOf(obj);
            if(StringUtils.isEmpty(str) || str.equals("null")){
                throw new RuntimeException("提交的数据中存在空值！");
            }
        }
    }

}
