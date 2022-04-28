package com.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenzhangzhong
 * @date 2021/12/15
 * @description 时间工具类
 */
public class DateUtils {

    /**
     * 获取两个日期字符串之间的日期集合
     *
     * @param startTime:String
     * @param endTime:String
     * @return list:yyyy-MM-dd
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取不中断的最大连续天数
     * 签到代表1,签到中断代表0,将整个签到数据拼接成一个10111011这样的字符串,
     * 然后以'0'切割字符串得到一个字符串数组,
     * 后面再计算最长字符串就得到最大连续天数了
     * <p>
     * 循环遍历,将签到的日期拿 1 来补位代表有签到, 如果下一个签到日期不是当前签到日期的下一天,那 用 0 补位,代表签到中断过
     * 举个例子
     * 签到数据
     * 1   3 4 5   7 8   19 20
     * 那我们要得到的一个字符串是
     * 1 0 1 1 1 0 1 1 0 1  1
     *
     * @param dateList 签到日期集合
     * @return
     * @throws ParseException
     */
    private static Integer getMaxUninterruptedDaysByOneZero(List<String> dateList) throws ParseException {

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 遍历签到日期
        for (int i = 0; i < dateList.size(); i++) {
            Date currentItem = dateFormat.parse(dateList.get(i));
            sb.append("1");
            Date nextItem = null;
            // 如果到最后一项了那下一项就是当前项了
            if ((i + 1) == dateList.size()) {

                nextItem = currentItem;
            } else {

                nextItem = dateFormat.parse(dateList.get(i + 1));
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentItem);
            calendar.add(Calendar.DATE, 1);
            Date nextDay = calendar.getTime();

            // 如果nextDay不等于nextItem, 代表签到中断, 拿 0 补位 (中断多少天其实咱们不关心)
            if (nextItem.compareTo(nextDay) != 0 && i < dateList.size() - 1) {

                sb.append("0");
            }
        }
        // 取最大天数
        String s = Arrays.stream(sb.toString().split("0")).max(Comparator.naturalOrder()).get();
        return s.length();
    }

    /**
     * 毫秒转时分秒
     *
     * @param ms 毫秒
     * @return
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }
}
