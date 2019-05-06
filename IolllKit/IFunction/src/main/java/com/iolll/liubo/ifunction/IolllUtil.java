package com.iolll.liubo.ifunction;

/**
 * Created by LiuBo on 2019/4/11.
 */
public  class IolllUtil {
    public static <T, Return> Return runR(T t, IFunction.RunR<T, Return> runR) {
        return runR.run(t);
    }
    public static <T> void run(T t, IFunction.Run<T> runR) {
        runR.run(t);
    }
    public static <T> T apply(T t, IFunction.Apply<T> apply) {
        return apply.apply(t);
    }



}
