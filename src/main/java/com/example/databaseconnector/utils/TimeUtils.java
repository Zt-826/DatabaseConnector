package com.example.databaseconnector.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

    static {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * 獲取當前時間
     *
     * @return
     */
    public static String getCurrentTime() {
        return sdf.format(new Date());
    }
}
