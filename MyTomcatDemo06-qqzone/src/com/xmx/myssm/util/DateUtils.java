package com.xmx.myssm.util;

import com.xmx.myssm.exception.BeanDateException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private final static SimpleDateFormat PATTERN_USE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getStrByDate(Date d) {
        try {
            return PATTERN_USE.format(d);
        } catch (Exception e) {
            throw new BeanDateException(e.getMessage());
        }
    }
}
