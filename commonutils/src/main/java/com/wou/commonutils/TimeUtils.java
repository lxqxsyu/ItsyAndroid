package com.wou.commonutils;/*
 * Copyright (c) 2014, 西安蘑菇科技有限公司 All rights reserved.
 * File Name：EditTextShakeHelper.java
 * Version：V1.0
 * Author：zshu 
 * Date： 创建时间：2015/11/27 14:57
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * TimeUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_MY = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_DATE_DDMM = new SimpleDateFormat("dd/MM");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(Long timeInMillis, SimpleDateFormat dateFormat) {
        if (timeInMillis == null) {
            return "";
        }
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * 获取年龄值
     *
     * @param birthday
     * @return
     */
    public static int getAge(String birthday) {
        if (StringUtils.isEmpty(birthday)) return 0;
        String yearStr = birthday.substring(0, 4);
        int integer = Integer.parseInt(yearStr);
        int newYear = new GregorianCalendar().get(Calendar.YEAR);
        return newYear - integer;
    }


    /**
     * 转换日期格式到用户体验好的时间格式
     * @param time 2015-04-11 12:45:06
     * @return
     */
    /*public static String dateString2GoodExperienceFormat(String time) {
        if (isNullString(time)) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            try {
                String timeString;
                Date parse = simpleDateFormat.parse(time);
                long distanceTime = new Date().getTime() - parse.getTime();
                if (distanceTime < 0L) {
                    timeString = "0 mins ago";
                } else {
                    long n2 = distanceTime / 60000L;
                    new SimpleDateFormat("HH:mm");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
                    if (n2 < 60L) {
                        timeString = String.valueOf(n2) + " mins ago";
                    } else if (n2 < 720L) {
                        timeString = String.valueOf(n2 / 60L) + " hours ago";
                    } else {
                        timeString = simpleDateFormat2.format(parse);
                    }
                }
                return timeString;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
    }
*/

    /**
     * 转换日期格式到用户体验好的时间格式
     *
     * @param timeLong 2015-04-11 12:45:06
     * @return
     */
    public static String dateString2GoodExperienceFormat(Long timeLong) {
        String time = "";
        if (time != null) {
            time = getTime(timeLong);
        }

        if (isNullString(time)) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            try {
                String timeString;
                Date parse = simpleDateFormat.parse(time);
                long distanceTime = new Date().getTime() - parse.getTime();
                if (distanceTime < 0L) {
                    timeString = "刚刚";
                } else {
                    long n2 = distanceTime / 60000L;
                    new SimpleDateFormat("HH:mm");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
                    if (n2 < 60L) {
                        timeString = String.valueOf(n2) + "分钟前";
                    } else if (n2 < 720L) {
                        timeString = String.valueOf(n2 / 60L) + "小时前";
                    } else {
                        timeString = simpleDateFormat2.format(parse);
                    }
                }
                return timeString;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
    }

    /**
     * 转换日期格式到用户体验好的时间格式
     *
     * @param time 2015-04-11 12:45:06
     * @return
     */
    public static String dateString2GoodExperienceFormat(String time) {

        if (isNullString(time)) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            try {
                String timeString;
                Date parse = simpleDateFormat.parse(time);
                long distanceTime = new Date().getTime() - parse.getTime();
                if (distanceTime < 0L) {
                    timeString = "刚刚";
                } else {
                    long n2 = distanceTime / 60000L;
                    new SimpleDateFormat("HH:mm");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd");
                    if (n2 < 60L) {
                        timeString = String.valueOf(n2) + "分钟前";
                    } else if (n2 < 720L) {
                        timeString = String.valueOf(n2 / 60L) + "小时前";
                    } else {
                        timeString = simpleDateFormat2.format(parse);
                    }
                }
                return timeString;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
    }


    /**
     * 获取时间差
     *
     * @param createdTime 开始时间
     * @return 返回表示时间段的字符串，例如：2小时20分钟 前
     */
    public static String cntTimeDifference(String createdTime, String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calNow = Calendar.getInstance();
        Calendar calCreated = Calendar.getInstance();
        try {
            calCreated.setTime(sdf.parse(createdTime));
            int year = calNow.get(Calendar.YEAR)
                    - calCreated.get(Calendar.YEAR);
            int month = calNow.get(Calendar.MONTH)
                    - calCreated.get(Calendar.MONTH);
            int day = calNow.get(Calendar.DAY_OF_MONTH)
                    - calCreated.get(Calendar.DAY_OF_MONTH);
            int hour = calNow.get(Calendar.HOUR_OF_DAY)
                    - calCreated.get(Calendar.HOUR_OF_DAY);
            int minute = calNow.get(Calendar.MINUTE)
                    - calCreated.get(Calendar.MINUTE);
            int total = minute + hour * 60 + day * 24 * 60 + month * 30 * 24
                    * 60 + year * 365 * 24 * 60;
            if (total > 365 * 24 * 60) {
                return total / (365 * 24 * 60) + "年" + suffix;
            }
            if (total > 30 * 24 * 60) {
                return total / (30 * 24 * 60) + "月" + suffix;
            }
            if (total > 24 * 60) {
                return total / (24 * 60) + "天" + suffix;
            }
            if (total > 60) {
                return total / 60 + "小时" + suffix;
            }
            if (total > 0) {
                return total + "分钟" + suffix;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "未知时间";
        }
        return "刚刚";
    }

    public static boolean isNullString(String s) {
        return s == null || s.equals("") || s.equals("null");
    }


    public static String getNewsTime(String time) {
        if (time == null || time.equals("")) {
            return null;
        }
        String temp = time.substring(time.indexOf('-') + 1, time.length());
        temp.lastIndexOf(':');
        return temp.substring(0, temp.lastIndexOf(':'));
    }


    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
