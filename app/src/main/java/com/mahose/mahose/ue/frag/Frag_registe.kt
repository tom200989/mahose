package com.mahose.mahose.ue.frag

import android.text.TextUtils
import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import com.mahose.mahose.helper.LoginHelper
import kotlinx.android.synthetic.main.frag_registe.*

/*
 * Created by 54484 on 11/4/2021.
 */
class Frag_registe : RootFrag() {
    override fun onInflateLayout(): Int {
        return R.layout.frag_registe
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initListener() // 初始化监听
    }

    /**
     * 初始化监听
     */
    private fun initListener() {
        // 已有账户
        tv_register_had_account.setOnClickListener {
            toFrag(javaClass, Frag_login::class.java, null, true, 0)
        }
        // 获取验证码
        bt_register_get_code.setOnClickListener { getVerifyCode() }
        // 注册按钮
        bt_register.setOnClickListener { toRegister() }
    }

    /**
     * 获取验证码
     */
    private fun getVerifyCode() {
        // TODO: 11/4/2021  5分钟的限时判断, 未到5分钟, 不再发起获取验证码请求
        // 获取邮箱
        val email = et_register_email.text.toString()
        if (TextUtils.isEmpty(email)) toast(R.string.email_is_empty, 3000)
        // TODO: 11/4/2021  邮箱的正则校验
        else{
            // 发起请求
            val verifyHelper = LoginHelper(activity)
            verifyHelper.onGetVerifySuccess={
                toast(R.string.get_verify_success,5000)
            }
            verifyHelper.onGetVerifyFailed = {
                toast(R.string.get_verify_failed,5000)
            }
            verifyHelper.getVerify(email)
        }
       
        
    }

    /**
     * 注册
     */
    private fun toRegister() {
        // 获取编辑域
        val email = et_register_email.text.toString()
        val verifycode = et_register_verify_code.text.toString()
        val username = et_register_username.text.toString()
        val password = et_register_password.text.toString()
        val repassword = et_register_repassword.text.toString()
        // 非空判断
        if (TextUtils.isEmpty(email)) toast(R.string.email_is_empty, 3000)
        else if (TextUtils.isEmpty(verifycode)) toast(R.string.verify_code_is_empty, 3000)
        else if (TextUtils.isEmpty(username)) toast(R.string.username_is_empty, 3000)
        else if (TextUtils.isEmpty(password)) toast(R.string.password_is_empty, 3000)
        else if (TextUtils.isEmpty(repassword)) toast(R.string.re_password_is_empty, 3000)
        else if (!password.equals(repassword)) toast(R.string.twice_password_is_not_same, 3000)
        else{
            // TODO: 11/4/2021 正则校验
            // 发起注册请求
            val registerHelper = LoginHelper(activity)
            registerHelper.onPrepareListener = { wd_register_wait.showVisible() }
            registerHelper.onEndListener = { wd_register_wait.showGone() }
            registerHelper.onRegisterSuccess = {// 跳转到登录页
                toast(R.string.register_success, 5000)
                toFrag(javaClass, Frag_login::class.java, null, true, 0)
            }
            registerHelper.onRegisterFailed = { toast(R.string.register_failed, 5000) }
            registerHelper.register(email, username, password)
        }
        
    }

    override fun onBackPresss(): Boolean {
        // 回退到上一级
        toFrag(javaClass, lastFrag, null, true, 0)
        return true
    }
}
