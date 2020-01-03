package com.wxy.newprictice.util;

import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取系统当前时间（12小时制）
     * 区别：12小时制使用小写的hh，24小时制使用大写的HH
     * @return  系统当前时间(年月日 时分秒)
     */
    public static String getCurrent12DateTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取系统当前时间（24小时制）
     * 区别：12小时制使用小写的hh，24小时制使用大写的HH
     * @return  系统当前时间(年月日 时分秒)
     */
    public static String getCurrent24DateTotal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取系统当前时间
     * @return  系统当前时间(年月日)
     */
    public static String getCurrentDateHalf() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
