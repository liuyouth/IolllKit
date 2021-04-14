package com.iolll.liubo.kt

import android.widget.ImageView
import java.io.Serializable

/**
 * Created by LiuBo on 2019-11-05.
 */

/**
 * 将类型转换的Apply
 * Apply 有返回值返回值是其本身
 *
 * @param <T>
</T> */
interface CallIO<T, R> : Serializable {
    fun call(t: T): R
}

interface CallI<T> : Serializable {
    fun call(t: T)
}

fun defultCall(str: String): Boolean {
    return str.isEmptyIO()
}

fun String?.isEmptyIO(): Boolean {
    return this?.isEmpty() ?: true
}

fun Boolean?.isTure(): Boolean {
    return this ?: false
}

fun <T> Any?.asT(): T? {
    return if (this==null){
        null
    }else this as T
}

fun ImageView.setImageDrawableID(typeIc: Int) {

    this.setImageDrawable(this.context.resources.getDrawable(typeIc))
}
