package com.mahose.mahose.helper

import android.app.Activity
import com.hiber.tools.TimerHelper

/*
 * Created by 54484 on 11/15/2021.
 */
class PayHelper : BaseHelper() {

    var onPaySuccess: (() -> Unit)? = null // 付款成功
    var onPayFailed: (() -> Unit)? = null // 付款失败

    /**
     * 付款
     */
    fun pay(activity: Activity) {
        val test_pay = false
        // TODO: 11/15/2021  生成paypal所需的信息
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_pay) {
                    onPaySuccess?.invoke()
                } else {
                    onPayFailed?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }
}
