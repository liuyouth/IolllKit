package com.iolll.liubo.ifunction;

import java.io.Serializable;

/**
 * Created by LiuBo on 2019/4/11.
 */
public interface IFunction {
    /**
     * 将类型转换的Apply
     * Apply 有返回值返回值是其本身
     * @param <T>
     */
    public interface Apply<T> extends Serializable{
        T apply(T t);
    }
    public interface Null<T> extends Serializable{
        T getNew();
    }
    public interface NullRun extends Serializable {
        void run();
    }
    /**
     * run
     * @param <T>
     */
    public interface Run<T> extends Serializable {
        void run(T t);
    }
    public interface RunR<T,R> extends Serializable{
        R run(T t);
    }

}
