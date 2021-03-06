package com.mahose.mahose.helper

import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.hiber.tools.TimerHelper

/*
 * Created by 54484 on 10/27/2021.
 */
public class LoginHelper(activity: Activity) : BaseHelper() {

    var onStateListener: ((state: LOGIN_ENUM) -> Unit)? = null // 获取登录登出注册状态
    var onLoginSuccessListener: (() -> Unit)? = null // 登录成功
    var onLoginFailedListener: (() -> Unit)? = null // 登录失败
    var onLogoutSuccessListener: (() -> Unit)? = null // 登出成功
    var onLogoutFailedListener: (() -> Unit)? = null // 登出失败
    var onGetVerifySuccess: (() -> Unit)? = null // 获取验证码成功
    var onGetVerifyFailed: (() -> Unit)? = null // 获取验证码失败
    var onRegisterSuccess: (() -> Unit)? = null // 注册成功
    var onRegisterFailed: (() -> Unit)? = null // 注册失败
    var onModifySuccess: (() -> Unit)? = null // 修改密码成功
    var onModifyFailed: (() -> Unit)? = null // 修改密码失败

    var activity: Activity? = null

    init {
        this.activity = activity
    }

    /**
     * 获取状态(登录/登出/未注册)
     */
    fun getState() {
        onPrepareListener?.invoke()
        // TODO: 10/27/2021  检查登录状态
        object : TimerHelper(activity) {
            override fun doSomething() {
                onStateListener?.invoke(LOGIN_ENUM.LOGIN)
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 登录
     */
    fun login(username: String, password: String) {
        val test_login = true // 模拟登录状态
        // TODO: 11/4/2021  请求登录
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_login) {
                    onLoginSuccessListener?.invoke()
                } else {
                    onLoginFailedListener?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 登出
     */
    fun logout(username: String) {
        val test_logout = true // 模拟登出状态
        // TODO: 11/4/2021  请求登出
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_logout) {
                    onLogoutSuccessListener?.invoke()
                } else {
                    onLogoutFailedListener?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 获取验证码
     */
    fun getVerify(email: String) {
        val test_verify = true
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_verify) {
                    onGetVerifySuccess?.invoke()
                } else {
                    onGetVerifyFailed?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 注册
     */
    fun register(email: String, username: String, password: String) {
        val test_register = true // 模拟注册状态
        // TODO: 11/4/2021  注册请求
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_register) {
                    onRegisterSuccess?.invoke()
                } else {
                    onRegisterFailed?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 修改密码
     */
    fun modifyPassword(email: String, new_password: String) {
        val test_modify = true // 模拟修改状态
        // TODO: 11/4/2021  修改请求
        onPrepareListener?.invoke()
        object : TimerHelper(activity) {
            override fun doSomething() {
                if (test_modify) {
                    onModifySuccess?.invoke()
                } else {
                    onModifyFailed?.invoke()
                }
                onEndListener?.invoke()
            }
        }.startDelay(1000)
    }

    /**
     * 当前状态
     */
    public enum class LOGIN_ENUM {
        LOGIN, LOGOUT, UNREGISTER, UNKNOWN
    }
}
