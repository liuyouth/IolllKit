package com.iolll.liubo.niceutil;

import static com.iolll.liubo.niceutil.NiceUtil.isEmpty;

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
}
