/*
 * 文件名：DateUtil.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DateUtil.java
 * 修改人：duanji
 * 修改时间：2015-12-14
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date工具类.
 * <p>
 * 详细描述
 * <p>
 * 示例代码
 * 
 * @author tianzhong
 */
public class DateUtil {

    /**
     * 获取指定时间隔后的时间：分钟间隔.
     * 
     * @param startTime
     *            .
     * @param timeInterval
     *            分钟
     * @param endTime
     *            .
     * @return Date .
     * @throws ParseException
     *             .
     */
    public static Date getDateByIntervalM(Date startTime, int timeInterval, Date endTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        Date d = getSpecifiedDate(dateToString(startTime), 0, 0, 0, 0, timeInterval, 0); // 开始下一天
        if (d.compareTo(endTime) < 0) { // 到达24点
            cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + timeInterval); // 加上分钟数
        } else {
            return endTime;
        }

        return cal.getTime();
    }

    /**
     * 获取指定时间隔后的时间：秒间隔 .
     * 
     * @param startTime
     *            .
     * @param timeInterval
     *            秒
     * @param endTime
     *            .
     * @return Date .
     * @throws ParseException
     *             .
     */
    public static Date getDateByIntervalS(Date startTime, int timeInterval, Date endTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        Date d = getSpecifiedDate(dateToString(startTime), 0, 0, 0, 0, 0, timeInterval); // 开始下一天
        if (d.compareTo(endTime) < 0) {
            cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + timeInterval); // 加上秒数
        } else {
            return endTime;
        }
        return cal.getTime();
    }

    /**
     * 或取当前 年月日 + 00:00:00 .
     * 
     * @return Date .
     */
    public static Date getDateNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        return cal.getTime();
    }

    /**
     * TODO 获取某一时间基础上指定的时间.
     * 
     * @param dateTime
     *            .
     * @param year
     *            .
     * @param month
     *            .
     * @param day
     *            .
     * @param hour
     *            .
     * @param minute
     *            .
     * @param second
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getSpecifiedDate(String dateTime, int year, int month, int day, int hour, int minute, int second) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return calendar.getTime();

    }

    /**
     * TODO 获取某一个月的开始时间.
     * 
     * @param dateTime
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getStartThisMonth(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    /**
     * 下个月第一天 .
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getNextMonthStart(String time) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf1.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return getStartThisMonth(sdf2.format(calendar.getTime()));

    }

    /**
     * 某个月的月末.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getLastThisMonth(String time) throws ParseException {
        Date date = getNextMonthStart(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        // calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();

    }

    /**
     * 获取到月初的天数.
     * 
     * @param orderTime
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static int getDaysByDate(String orderTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(orderTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时间是否相同.
     * 
     * @param startTime
     *            .
     * @param endTime
     *            .
     * @return .
     */
    public static boolean isSameTime(Date startTime, Date endTime) {
        if (startTime.compareTo(endTime) == 0) {
            return true;
        }
        return false;

    }

    /**
     * 指定格式的时间字符串 .
     * 
     * @param time
     *            .
     * @param sign
     *            .
     * @return .
     */
    public static String getTimeStr(String time, String sign) {
        return time.substring(0, 4) + sign + time.substring(4, 6) + sign + time.substring(6, 8);
    }

    /**
     * 一天的开始时间.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getStartThisDay(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    /**
     * 一天的结束.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getLastThisDay(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == time && "".equals(time)) {
            time = dateToString(new Date());
        }
        Date date = sdf.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();

    }

    /**
     * 获取前一天.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getBefore(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == time && "".equals(time)) {
            time = dateToString(new Date());
        }
        return getStartThisDay(sdf.format(getSpecifiedDate(time, 0, 0, -1, 0, 0, 0)));

    }

    /**
     * 获取下一天.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getNextDay(Date time) throws ParseException {

        return getSpecifiedDate(dateToString(getStartThisDay(dateToString(time))), 0, 0, 1, 0, 0, 0);

    }

    /**
     * .
     * 
     * @param time
     *            .
     * @return .
     */
    public static String dateToString(Date time) {
    	if (time == null) {
			return "";
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    
    /**
     * .
     * 
     * @param time
     *            .
     * @return .
     */
    public static String dateToStringYMD(Date time) {
    	if (time == null) {
			return "";
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    /**
     * 是否是月末.
     * 
     * @param time
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static boolean isMonthEnd(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);

        if (date.compareTo(getLastThisMonth(time)) >= 0) {
            return true;
        }
        return false;

    }

    /**
     * 获取某一时间之前的多少天的时间 .
     * 
     * @param time
     *            .
     * @param days
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getBeforeDays(String time, int days) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        return getSpecifiedDate(dateToString(getStartThisDay(dateToString(date))), 0, 0, days, 0, 0, 0);
    }

    /**
     * 获取某一时间之前的多少分钟的时间 .
     * 
     * @param time
     *            .
     * @param minute
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getBeforeMinutes(String time, int minute) throws ParseException {
        return getSpecifiedDate(time, 0, 0, 0, 0, minute, 0);
    }

    /**
     * @param date
     *            .
     * @return .
     * @throws ParseException
     *             .
     */
    public static Date getBeforeMonthStart(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNextMonthStart(dateToString(date)));
        calendar.set(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    /**
     * 两个时间之间的间隔天数.
     * 
     * @param date1
     *            .
     * @param date2
     *            .
     * @return .
     */
    public static long getIntervalDays(Date date1, Date date2) {
        long days = Math.abs((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24);
        return days;

    }

    /**
     * TODO 通过日期字符串获取日期.
     * 
     * @param dateStr
     *            日期字符串
     * @return 日期对象
     * @throws ParseException
     *             .
     */
    public static Date getDateByDateStr(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);

    }

    /**
     * TODO 通过日期字符串获取日期.
     * 
     * @param param
     *            日期字符串
     * @return 日期对象
     * @throws ParseException
     *             异常
     */
    public static Date stringToDate(String param) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(param);
    }

    /**
     * TODO 某一时间小于某一时间减去间隔.
     * 
     * @param date
     *            时间
     * @param anotherDate
     *            时间
     * @param minInterval
     *            间隔时间秒
     * @return boolean
     * @throws ParseException
     *             ParseException
     */
    public static boolean isSmallerAfterInterval(Date date, Date anotherDate, int minInterval) throws ParseException {
        Date tempDate = new Date();
        if (anotherDate == null) {
            tempDate = getSpecifiedDate(dateToString(new Date()), 0, 0, 0, 0, 0, -minInterval);
        } else {
            tempDate = getSpecifiedDate(dateToString(anotherDate), 0, 0, 0, 0, 0, -minInterval);
        }
        if (date.compareTo(tempDate) <= 0) {
            return true;
        }
        return false;

    }

    /**
     * TODO 两个时间是否是同一个月.
     * 
     * @param oneDateStr
     *            dateStr
     * @param anotherDateStr
     *            anotherDateStr
     * @return boolean
     * @throws ParseException
     *             ParseException
     */
    public static boolean isSameMounth(String oneDateStr, String anotherDateStr) throws ParseException {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(stringToDate(oneDateStr));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(stringToDate(anotherDateStr));
        if (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
            return true;
        }
        return false;
    }

}
