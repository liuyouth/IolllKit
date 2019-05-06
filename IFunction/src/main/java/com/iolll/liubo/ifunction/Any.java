package com.iolll.liubo.ifunction;




/**
 * Created by LiuBo on 2018/12/2.
 */
public class Any<T> {
    public T it;

    public Any(T t) {
        this.it = t;
    }

    public static <T> Any<T> ins(T t) {
        return new Any<>(t);
    }
    public Any<T> whenNotNull(IFunction.Apply<T> apply) {
        if (it != null)
            it = apply.apply(it);
        return this;
    }

    public Any<T> whenNull(IFunction.Null<T> apply) {
        if (it == null)
            it = apply.getNew();
        return this;
    }


    //因为是空所以 可能会重新赋值
    public static Any<String> whenEmpty(String code, IFunction.Null<String> apply) {
        if(code==null || "".equals(code))
            code = apply.getNew();
        return Any.ins(code);
    }
    // 无返回值
    public static Any<String> whenNotEmpty(String code, IFunction.Run<String> apply) {
        if(null!=code && !"".equals(code))
            apply.run(code);
        return Any.ins(code);
    }


}
