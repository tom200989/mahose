package com.mahose.mahose.ue.frag

import android.text.TextUtils
import android.view.View
import com.hiber.hiber.RootFrag
import com.hiber.hiber.language.RootApp
import com.mahose.mahose.R
import com.mahose.mahose.helper.LoginHelper
import kotlinx.android.synthetic.main.frag_login.*

/*
 * Created by 54484 on 11/2/2021.
 */
class Frag_login : RootFrag() {

    // 状态
    var enum: LoginHelper.LOGIN_ENUM = LoginHelper.LOGIN_ENUM.LOGOUT

    override fun onInflateLayout(): Int {
        return R.layout.frag_login
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initData() // 初始数据(头像等)
        initListener() // 初始化监听
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // TODO: 11/4/2021  读取本地头像
    }

    /**
     * 初始化监听
     */
    private fun initListener() {
        // 点击登录
        bt_login.setOnClickListener { toLogin() }
        // 出错界面
        wd_login_error.onTryAaginListener = { toLogin() }
        // 忘记密码
        tv_login_forgot.setOnClickListener {
            // TODO: 11/3/2021  前往忘记密码界面
        }
    }

    /**
     * 触发登录
     */
    private fun toLogin() {
        val username = et_login_username.text.toString()
        val password = et_login_password.text.toString()
        // 非空判断
        if (TextUtils.isEmpty(username)) toast(getString(R.string.username_is_empty), 3000)
        if (TextUtils.isEmpty(password)) toast(getString(R.string.password_is_empty), 3000)
        // 触发
        val loginHelper = LoginHelper(activity)
        loginHelper.onPrepareListener = { wd_login_wait.showVisible() }
        loginHelper.onEndListener = { wd_login_wait.showGone() }
        loginHelper.onLoginSuccessListener = {// 登录成功
            enum = LoginHelper.LOGIN_ENUM.LOGIN
            toFrag(javaClass, lastFrag, enum, true, 0)
        } 
        loginHelper.onLoginFailedListener = { // 登录失败
            enum = LoginHelper.LOGIN_ENUM.LOGOUT
            wd_login_error.visibility = View.VISIBLE
        }
        loginHelper.login(username, password)
    }

    override fun onBackPresss(): Boolean {
        // 等待页面
        if (wd_login_wait.visibility == View.VISIBLE) return true
        // 错误页面
        if (wd_login_error.visibility == View.VISIBLE) {
            wd_login_error.visibility = View.GONE
            return true
        }
        // 跳转会上一页
        toFrag(javaClass, lastFrag, enum, true, 0)
        return true
    }

}
