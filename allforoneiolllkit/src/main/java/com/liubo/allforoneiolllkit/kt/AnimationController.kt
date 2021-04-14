package com.iolll.liubo.kt

import android.animation.Animator

/**
 * 动画控制器
 * Created by LiuBo on 2019-11-15.
 */
class AnimationController(var start: Animator, var end: Animator
                          , startListener: Animator.AnimatorListener?
                          , endListener: Animator.AnimatorListener?) {
    constructor(start: Animator, end: Animator) : this(start,end,null,null)
    var isDebug = false
    // 0 常规 1 展开 2 动画中...
    var status: Int = 0
    init {
        if (startListener != null)
            start.addListener(startListener)
        if (endListener != null)
            end.addListener(endListener)
    }
    fun start() {
        startAnimator(start, 1, 0)
    }
    fun end() {
        startAnimator(end, 0, 1)
    }
    private fun startAnimator(animator: Animator, endStatus: Int, cancelStatus: Int) {
        if (2 == status) {
            printLog("动画进行中... 该操作无效")
        } else {
            status = 2
            animator.addListener(getAnimatorListener(cancel = { status = cancelStatus }, end = { status = endStatus }))
            animator.start()
        }
    }
    private fun printLog(s: String) {
        if (isDebug) println(s)
    }
}