package com.qqj.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils
{

    public static void main(String[] args) throws ParseException
    {
    }

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前系统时间 Timestamp
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:52:47
     * @return
     */
    public static Timestamp getSystemTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间，去掉毫秒
     *
     *
     * @author qqjbest
     * @since 2017年3月27日 下午7:01:05
     * @return
     */
    public static Timestamp getShortSystemTime()
    {
        return Timestamp.valueOf(format.format(new Date()));
    }

    public static Date parserString2Date(String text) throws ParseException
    {
        return format.parse(text);
    }

    public static Date parseString2yyyyMMdd(String text) throws ParseException
    {
        return yyyy_MM_dd.parse(text);
    }

    /**
     * 获取当前系统的日期 yyyyMMdd
     *
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:53:05
     * @return
     */
    public static String getCurrentDate()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(getSystemTime());
    }

    /**
     * 时间格式化 yyyy年M月d日 h:m:s
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:56:19
     * @param t
     * @return
     */
    public static String TimeForMat(Date t)
    {
        if (t != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            return sdf.format(t);
        }
        return "";
    }

    /**
     * 日期格式化
     *
     * @author qqjbest
     * @since 2016年10月24日 下午1:26:10
     * @param t
     * @return
     */
    public static String DateForMat(Date t)
    {
        if (t != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            return sdf.format(t);
        }
        return "";
    }

    /**
     * 时间转换 long 转 String
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:55:35
     * @param time
     * @return
     */
    public static String parseTime(long time)
    {
        Date dt = new Date(time);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(dt);
    }

    /**
     * 时间转换 （String 转 long）
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:54:53
     * @param time
     * @return
     */
    public static long parseTime(String time)
    {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        try
        {
            return fmt.parse(time).getTime();
        }
        catch (ParseException e)
        {
            return 0;
        }
    }

    /**
     * 时间转换 （Timestamp 转 String）
     *
     * @author qqjbest
     * @since 2016年4月11日 下午3:57:52
     * @param t
     * @return
     */
    public static String parseTime(Timestamp t)
    {
        if (t != null)
        {
            String s = t.toString();
            return s.substring(0, s.lastIndexOf("."));
        }
        return "";
    }

    /**
     * 分钟比较
     *
     * @author qqjbest
     * @since 2016年4月11日 下午4:00:32
     * @param from
     * @param to
     * @param mins
     * @return
     */
    public static boolean compareByMins(Timestamp from, Timestamp to, int mins)
    {
        if (from == null || to == null)
        {
            return true;
        }
        return (to.getTime() - from.getTime()) >= mins * 60 * 1000;
    }

    /**
     * 日期比较
     *
     * @author qqjbest
     * @since 2016年4月11日 下午4:00:48
     * @param from
     * @param to
     * @param days
     * @return
     */
    public static boolean compareByDays(Timestamp from, Timestamp to, int days)
    {
        if (from == null || to == null)
        {
            return true;
        }

        return (to.getTime() - from.getTime()) >= days * 24 * 60 * 60 * 1000;
    }

    /**
     * 获取今天的截止时间
     *
     * @author qqjbest
     * @since 2017年6月11日 下午7:09:43
     * @return
     */
    public static Long getTodayEndTime()
    {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获取某天的截止时间
     *
     * @author qqjbest
     * @since 2017年6月11日 下午7:10:32
     * @param date
     * @return
     */
    public static Long getOneDayEndTime(Date date)
    {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 是否是日期
     *
     * @author qqjbest
     * @since 2016年12月26日 上午10:39:21
     * @return
     */
    public static boolean isDate(String date)
    {
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(date);

        return mat.matches();
    }

    /**
     * 获取本月的第一天
     *
     * @author qqjbest
     * @since 2016年12月26日 上午11:25:01
     * @return
     */
    public static Long currMonthFirst()
    {
        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime().getTime();
    }

    /**
     * 获取本月的最后一天
     *
     * @author qqjbest
     * @since 2016年12月26日 上午11:25:01
     * @return
     */
    public static Long currMonthEnd()
    {
        // 获取当前月最后一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime().getTime();
    }

    public static Date getCurrDate() throws ParseException
    {
        return yyyy_MM_dd.parse(yyyy_MM_dd.format(new Date()));
    }

    /**
     * 获取本月的所有日期
     *
     * @author qqjbest
     * @since 2016年12月29日 上午11:14:42
     * @param date
     * @return
     * @throws ParseException
     */
    public static List<Long> getAllTheDateOftheMonth(Date date) throws ParseException
    {

        List<Long> list = new ArrayList<Long>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        int month = cal.get(Calendar.MONTH);
        while (cal.get(Calendar.MONTH) == month)
        {
            list.add(cal.getTime().getTime());
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 获取本月的第一天是星期几
     *
     * @author qqjbest
     * @since 2016年12月29日 下午1:45:20
     * @return
     */
    public static byte getFirstDayOfMonth()
    {
        /** 获取日历实例 **/
        Calendar cld = Calendar.getInstance();
        /** 设置日历成当月的第一天 **/
        cld.set(Calendar.DAY_OF_MONTH, 1);
        // 星期对应数字
        return (byte) (cld.get(Calendar.DAY_OF_WEEK) - 1);

    }

    /**
     * 获取本月有几天
     *
     * @author qqjbest
     * @since 2016年12月29日 下午1:56:26
     * @return
     */
    public static int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取日期在当月的第几天
     *
     * @author qqjbest
     * @since 2016年12月29日 下午4:00:34
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getBeforeDayDate(Date date, int beforeDay)
    {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.add(Calendar.DATE, -beforeDay);
        return a.getTime();
    }

    /**
     * 获取日期对应的是星期几
     *
     * @author qqjbest
     * @since 2017年1月16日 下午2:09:10
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 时间上添加N个小时
     *
     * @author qqjbest
     * @since 2017年6月11日 下午2:04:19
     * @param startTime
     * @param hour
     * @return
     */
    public static Timestamp addNHour(Timestamp startTime, int hour)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(startTime);
        ca.add(Calendar.HOUR_OF_DAY, hour);
        return new Timestamp(ca.getTime().getTime());
    }

    public static Timestamp addNHour(Date startTime, int hour)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(startTime);
        ca.add(Calendar.HOUR_OF_DAY, hour);
        return new Timestamp(ca.getTime().getTime());
    }

    /**
     * 时间上年，月加减
     *
     * @author qqjbest
     * @since 2017年6月16日 下午4:58:55
     * @param time
     * @param year
     * @param month
     * @return
     */
    public static Date addYearMonth(Date time, Integer year, Integer month)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        if (ValidatorUtil.isNotNull(year))
        {
            ca.add(Calendar.YEAR, year);
        }
        if (ValidatorUtil.isNotNull(month))
        {
            ca.add(Calendar.MONTH, month);
        }
        return ca.getTime();
    }

    /**
     * 获取某个时间的小时
     *
     * @author qqjbest
     * @since 2017年6月11日 下午7:00:24
     * @param date
     * @return
     */
    public static Integer getHourOfDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 计算两个时间相差几个小时
     *
     * @author qqjbest
     * @since 2017年6月11日 下午7:21:20
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long hoursOfTwo(Date startDate, Date endDate)
    {
        return (startDate.getTime() - endDate.getTime()) / (60 * 60 * 1000);
    }

    /**
     * 获取某个小时的时间
     *
     * @author qqjbest
     * @since 2017年6月11日 下午7:00:24
     * @param date
     * @return
     */
    public static Timestamp getDayOneTime(Date date, int hour)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        return new Timestamp(c.getTime().getTime());
    }

}
