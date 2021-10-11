package com.mahose.mahose.helper

/*
 * Created by PD on 2021/10/9.
 */
class UpdateHelper {

    var onCheckStartListener: (() -> Unit)? = null // 开始检查
    var onCheckEndListener: ((need: Boolean) -> Unit)? = null // 检查结束(@param: 是否需要升级)

    var onUpdateStartListener: (() -> Unit)? = null // 开始升级
    var onUpdateProgressListener: ((progress: Int) -> Unit)? = null // 升级进度
    var onUpdateFinishListener: (() -> Unit)? = null // 升级结束
    var onUpdateErrorListener: (() -> Unit)? = null // 升级出错

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
    fun update() {
        onUpdateStartListener?.invoke()
        onUpdateProgressListener?.invoke(50)
        onUpdateFinishListener?.invoke()
        onUpdateErrorListener?.invoke()
    }
}
