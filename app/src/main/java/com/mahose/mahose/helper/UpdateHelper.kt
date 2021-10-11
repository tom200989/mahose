package com.mahose.mahose.helper

import android.app.Activity
import com.hiber.tools.TimerHelper

/*
 * Created by PD on 2021/10/9.
 */
class UpdateHelper {

    var onCheckStartListener: (() -> Unit)? = null // 开始检查
    var onCheckEndListener: ((need: Boolean) -> Unit)? = null // 检查结束(@param: 是否需要升级)

    var onUpdateStartListener: (() -> Unit)? = null // 开始升级
    var onUpdateProgressListener: ((progress: Int) -> Unit)? = null // 升级进度
    var onUpdateFinishListener: (() -> Unit)? = null // 升级结束
    var onUpdateErrorListener: ((error: String) -> Unit)? = null // 升级出错

    /**
     * 检查升级
     */
    fun check() {
        // TODO: 2021/10/9  升级: 一天只需要检查1次
        onCheckStartListener?.invoke()
        onCheckEndListener?.invoke(true)
    }

    /**
     * 开始升级
     */
    fun update(activity: Activity) {

        onUpdateStartListener?.invoke()
        // TOAT: 模拟升级
        var count = 0
        val timerHelper = object : TimerHelper(activity) {
            override fun doSomething() {
                count += 1
                when {
                    count < 100 -> {
                        onUpdateProgressListener?.invoke(count)
                    }
                    count > 150 -> {
                        onUpdateErrorListener?.invoke("error msg had seem")
                    }
                    else -> {
                        onUpdateFinishListener?.invoke()
                    }
                }
            }
        }
        
        timerHelper.start(60)
        
    }
}
