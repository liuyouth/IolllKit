package com.iolll.liubo.ifunction;


/**
 * Created by LiuBo on 2018/12/2.
 */
public class AnyRun<T> {
    public T it;

    public AnyRun(T t) {
        this.it = t;
    }

    public static <T> AnyRun<T> ins(T t) {
        return new AnyRun<>(t);
    }

//    public  AnyRun<T> whenNotNull( IFunction.Apply<T> apply) {
//        if (it != null)
//            it = apply.apply(it);
//        return this;
//    }

    public void whenNotNull(IFunction.Run<T> apply) {
        if (it != null)
            apply.run(it);
    }

    public Any<T> whenNull(IFunction.NullRun n) {
        if (it == null)
            n.run();
        return Any.ins(it);
    }
}
