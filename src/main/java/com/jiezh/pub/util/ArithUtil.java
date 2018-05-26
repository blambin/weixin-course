package com.jiezh.pub.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 运算工具类
 * 
 * @ClassName: ArithUtil
 * @author 谷寅飞
 * @date 2016年4月26日 上午11:32:55
 *
 */
public class ArithUtil {
    /*
     * 小数精确的位数
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    /**
     * 提供精确的加法运算。 String
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static String strAdd(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * 对一个数字取精度
     * 
     * @param v
     * @param scale
     * @return
     */
    public static BigDecimal round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的减法运算。String
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static String strSub(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 提供精确的乘法运算。 保留scale 位小数
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul2(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 提供精确的乘法运算。 保留scale 位小数 String
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static String strMul2(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static BigDecimal div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static String strDiv(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal bigDiv(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 取余数 string
     * 
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal strRemainder(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 取余数 string
     * 
     * @param v1
     * @param v2
     * @param scale
     * @return string
     */
    public static String strRemainder2Str(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 比较大小 如果v1 大于v2 则 返回true 否则false
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static boolean strcompareTo(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj > 0) res = true;
        else res = false;
        return res;
    }

    /**
     * 比较大小 如果v1 大于等于v2 则 返回true 否则false
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static boolean strcompareTo2(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj >= 0) res = true;
        else res = false;
        return res;
    }

    /**
     * 比较大小 如果v1 等于v2 则 返回true 否则false
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static boolean strcompareTo3(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj == 0) res = true;
        else res = false;
        return res;
    }

    /**
     * 取余数 BigDecimal
     * 
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal bigRemainder(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.remainder(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (v == null) {
            v = new BigDecimal(0);
        }
        BigDecimal one = new BigDecimal("1");
        return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的小数位四舍五入处理。string
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String strRound(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供N次方的运算
     * @Title: pow
     * @author 谷寅飞
     * @date 2016年4月27日 下午1:50:52
     * @param value
     * @param n
     * @return double
     * @throws
     */
    public static double pow(double value, int n) {
        BigDecimal bd1 = new BigDecimal(Double.toString(value));
        BigDecimal bd2 = bd1.pow(n);
        return bd2.doubleValue();
    }

    /**
     * 此方法用来开n次方
     * @Title: sqrt
     * @author 谷寅飞
     * @date 2016年4月28日 下午2:25:30
     * @param value
     * @param n
     * @return double
     * @throws
     */
    public static double sqrt(double value, int n) {
        BigDecimal bd1 = new BigDecimal(Double.toString(value));
        double bd2 = StrictMath.pow(bd1.doubleValue(), div(1.0d, n));
        return bd2;
    }

    public static BigDecimal sqrt(BigDecimal value, int n) {
        double bd2 = StrictMath.pow(value.doubleValue(), div(1.0d, n));
        return new BigDecimal(bd2);
    }

    /**
     * 计算普通年金现值
     * @Title: presentValue
     * @author 谷寅飞
     * @date 2016年4月27日 下午1:21:37
     * @param rate 利率
     * @param nper 剩余期数
     * @param pmt 每期金额
     * @return double 现值
     * @throws
     */
    public static double presentValue(double rate, int nper, double pmt) throws Exception {
        // =pmt*((1-(1+rate)^-nper)/rate)
        double pv = mul(pmt, div(sub(1, div(1, pow(add(1, rate), nper), 30)), rate, 30));
        return pv;
    }

    /**
     * 计算债权价值
     * @Title: valueOfCreditorsRights
     * @author 谷寅飞
     * @date 2016年4月27日 下午3:21:29
     * @param pv 现值
     * @param rate 利率
     * @param startDate 开始还款日期
     * @param totalPeriods 总期数
     * @param remainingPeriod 剩余期数
     * @return double 债权价值
     * @throws
     */
    public static double valueOfCreditorsRights(double pv, double rate, Date startDate, int totalPeriods, int remainingPeriod, Date today) {
        try {
            double coefficient = creditorsRightsCoefficient(rate, startDate, totalPeriods, remainingPeriod, today);
            double result = mul(pv, coefficient);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

    /**
     * 计算债权价值
     * @Title: valueOfCreditorsRights
     * @author 谷寅飞
     * @date 2016年5月4日 下午1:10:39
     * @param nper 剩余期数
     * @param pmt 每期金额
     * @param rate 月利率
     * @param startDate 开始还款日期
     * @param totalPeriods 总期数
     * @return double 债权价值
     * @throws
     */
    public static double valueOfCreditorsRights(int nper, double pmt, double rate, Date startDate, int totalPeriods, Date today) {
        try {
            double coefficient = creditorsRightsCoefficient(rate, startDate, totalPeriods, nper, today);
            double pv = presentValue(rate, nper, pmt);
            double result = mul(pv, coefficient);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

    /**
     * 债权价值系数，pv*本方法返回值=债权价值
     * @Title: creditorsRightsCoefficient
     * @author 谷寅飞
     * @date 2016年4月28日 下午5:48:47
     * @param rate 利率
     * @param startDate 开始还款日期
     * @param totalPeriods 总期数
     * @param remainingPeriod 剩余期数
     * @return double 债权价值
     * @throws
     */
    private static double creditorsRightsCoefficient(double rate, Date startDate, int totalPeriods, int remainingPeriod, Date today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();
        calendar.setTime(startDate); // 将日历设置为开始时间
        calendarNow.setTime(today); // 设置为当前时间
        try {
            today = sdf.parse(calendarNow.get(Calendar.YEAR) + "-" + (calendarNow.get(Calendar.MONTH) + 1) + "-" + calendarNow.get(Calendar.DATE));
            calendarNow.setTime(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH, totalPeriods - remainingPeriod - 1); // 最后一次还款的时间
        try {
            // 计算最后一次还款时间到当前时间的天数
            long milliseconds = calendar.getTimeInMillis();
            long millisecondsNow = calendarNow.getTimeInMillis();
            int subDayNum = (int) div(millisecondsNow - milliseconds, 86400000, 30);
            // -PV(K3,J3,G3)*(1+((1+K3)^(1/30)-1))^($Y$1-(EDATE(H3,F3-J3-1)))
            // -pv*(1+((1+rate)^(1/30)-1))^subDayNum
            double s = sqrt(add(1, rate), 30);
            double sub11 = sub(s, 1);
            double result = 0d;
            if (subDayNum < 0) {
                result = sqrt(add(1, (sub11)), -subDayNum);
            } else {
                result = pow(add(1, (sub11)), subDayNum);
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

    /**
     * 通过债权价值等反向计算月还
     * @Title: getMonthlyByCreditorsRights
     * @author 谷寅飞
     * @date 2016年4月28日 下午5:56:39
     * @param rights 债权价值
     * @param nper 剩余期数
     * @param rate 利率
     * @param startDate 开始还款日期
     * @param totalPeriods 总期数
     * @return double 月还
     * @throws
     */
    public static double getMonthlyByCreditorsRights(double rights, int nper, double rate, Date startDate, int totalPeriods, Date today) {
        double coefficient = creditorsRightsCoefficient(rate, startDate, totalPeriods, nper, today);
        double pv = div(rights, coefficient, 30);
        double pmt = div(pv, div(sub(1, div(1, pow(add(1, rate), nper), 30)), rate, 30), 30);
        return pmt;
    }

    /**
     * 两个日期相差月数
     * @Title: getMonth
     * @author 谷寅飞
     * @date 2016年5月4日 下午1:37:42
     * @param start
     * @param end
     * @return int
     * @throws
     */
    public static int getMonth(Date start, Date end) {
        // if (start.after(end)) {
        // Date t = start;
        // start = end;
        // end = t;
        // }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            double rate = 0.00833333333333333; // 利率
            int nper = 36; // 剩余期数
            double pmt = 2064.71; // 月还
            Date startDate = sdf.parse("2016-6-1"); // 第一次开始还款的时间
            int totalPeriods = 36; // 总期数
            double pv = presentValue(rate, nper, pmt);
            System.out.println("pv:" + pv);
            double r = valueOfCreditorsRights(pv, rate, startDate, totalPeriods, nper, new Date());
            System.out.println("债权价值:" + r);
            // double rpmt = getMonthlyByCreditorsRights(50000.00, nper, rate, startDate, totalPeriods);
            // System.out.println("改变后的月还:" + rpmt);
            int months = getMonth(startDate, new Date());
            System.out.println("月数：" + months);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * 取随机数
     * @Title: getRandom
     * @author 谷寅飞
     * @date 2016年7月14日 下午3:07:30
     * @param max
     * @param min
     * @return int
     * @throws
     */
    public static int getRandom(int max, int min) {
        Random ra = new Random();
        return ra.nextInt(max) + min;
    }

    /**
     * 获取固定位数的数字字符串，不足左侧补零
     * @Title: getNumberStr
     * @author 谷寅飞
     * @date 2016年7月19日 上午11:04:50
     * @param number
     * @param digit
     * @return String
     * @throws
     */
    public static String getNumberStr(int number, int digit) {
        // 得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(digit);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(digit);
        // 输出测试语句
        return nf.format(number);
    }

    // 流水号加1后返回，流水号长度为4
    private static final String STR_FORMAT = "0000";

    /**
     * 四位流水号加1格式化
     * @Title: haoAddOne
     * @author 谷寅飞
     * @date 2016年7月19日 上午10:59:04
     * @param liuShuiHao
     * @return String
     * @throws
     */
    public static String haoAddOne(String liuShuiHao) {
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }

    /**
     * 取两个数的最小值
     * @Title: getMinValue
     * @author 谷寅飞
     * @date 2016年7月14日 下午3:11:43
     * @param a
     * @param b
     * @return BigDecimal
     * @throws
     */
    public static BigDecimal getMinValue(BigDecimal a, BigDecimal b) {
        int result = a.compareTo(b);
        switch (result) {
            case 1:
                return b;
            case 0:
                return a;
            case -1:
                return a;
            default:
                return null;
        }
    }

}
