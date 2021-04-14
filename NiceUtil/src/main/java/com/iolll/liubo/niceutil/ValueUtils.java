package com.iolll.liubo.niceutil;

import java.util.List;

/**
 * Created by LiuBo on 2018/12/17.
 */
public class ValueUtils {
    public static String vDefault(String value) {
        return vDefault(value, "");
    }

    public static String vDefault(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value;
    }

    public static int vDefault(int value) {
        return vDefault(value, 0);
    }

    public static int vDefault(int value, int defaultValue) {
        return 0 == value ? defaultValue : value;
    }

    public static long vDefault(long value) {
        return vDefault(value, 0L);
    }

    public static long vDefault(long value, long defaultValue) {
        return 0L == value ? defaultValue : value;
    }

    public static double vDefault(double value) {
        return vDefault(value, 0D);
    }

    public static double vDefault(double value, double defaultValue) {
        return 0D == value ? defaultValue : value;
    }


    public static boolean isEmpty(Integer value) {
        return value == null || value == 0;
    }

    public static boolean isEmpty(Double value) {
        return value == null || value == 0;
    }

    public static boolean isEmpty(Float value) {
        return value == null || value == 0;
    }

    public static boolean isEmpty(String... strings) {
        for (String string : strings)
            if (isEmpty(string))
                return true;
        return false;
    }

    public static <T> boolean  isEmpty(List<T> datas) {
        return null == datas || datas.isEmpty();
    }





    public static boolean isEmpty(String msg) {
        return msg == null || msg.length() == 0;
    }


}
