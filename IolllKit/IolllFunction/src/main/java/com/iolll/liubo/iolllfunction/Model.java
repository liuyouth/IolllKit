package com.iolll.liubo.iolllfunction;

import com.iolll.liubo.ifunction.IFunction;

import java.io.Serializable;


/**
 * Created by LiuBo on 2018/12/3.
 */
public class Model<T> implements Serializable {
    public T apply(IFunction.Apply<T> apply) {
        return apply.apply((T) this);
    }

}

