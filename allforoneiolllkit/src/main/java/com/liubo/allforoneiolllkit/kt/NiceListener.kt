package com.iolll.liubo.kt

import android.animation.Animator
import android.text.Editable
import android.text.TextWatcher

/**
 * Created by LiuBo on 2019-11-06.
 */

fun getTextWatcherTextChangeUnit(textChangedCall: ((CharSequence?) -> Unit)? = null): TextWatcher {
    return getTextWatcher(before = textChangedCall)
}

fun getTextWatcher(after: ((Editable?) -> Unit)? = null, before: ((CharSequence?) -> Unit)? = null, textChanged: ((CharSequence?) -> Unit)? = null): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            after?.invoke(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            before?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged?.invoke(s.toString())
//        blankViewModel.setOriginalPassword(PasswordChange(nowPwEdit.text.toString(), pwEdit.text.toString(), reEdit.text.toString()))
        }
    }
}

fun getAnimatorListener(repeat: ((Animator?) -> Unit)? = null, start: ((Animator?) -> Unit)? = null,
                        cancel: ((Animator?) -> Unit)? = null, end: ((Animator?) -> Unit)? = null
): Animator.AnimatorListener {
    return object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            repeat?.invoke(animation)
        }
        override fun onAnimationEnd(animation: Animator?) {
            end?.invoke(animation)
        }
        override fun onAnimationCancel(animation: Animator?) {
            cancel?.invoke(animation)
        }
        override fun onAnimationStart(animation: Animator?) {
            start?.invoke(animation)
        }
    }
}
