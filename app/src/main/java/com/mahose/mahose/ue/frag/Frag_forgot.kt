package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import com.mahose.mahose.helper.LoginHelper
import kotlinx.android.synthetic.main.frag_forgot.*

/*
 * Created by 54484 on 11/5/2021.
 */
class Frag_forgot : RootFrag() {
    override fun onInflateLayout(): Int {
        return R.layout.frag_forgot
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化点击
        initListener()
    }

    private fun initListener() {
        // 获取验证码
        bt_forgot_verify.setOnClickListener { getVerify() }
        // 提交新密码
        bt_forgot_commit.setOnClickListener { commit() }
    }

    /**
     * 提交新密码
     */
    private fun commit() {
        val email = et_forgot_email.text.toString()
        val verify = et_forgot_verify_code.text.toString()
        val password = et_forgot_new_password.text.toString()
        if (email.isEmpty()) toast(R.string.email_is_empty, 3000)
        else if (verify.isEmpty()) toast(R.string.verify_code_is_empty, 3000)
        else if (password.isEmpty()) toast(R.string.password_is_empty, 3000)
            
        // TODO: 11/8/2021  正则表达式判断
        else{
            val loginHelper = LoginHelper(activity)
            loginHelper.onPrepareListener = {wd_forgot_wait.showVisible()}
            loginHelper.onEndListener = {wd_forgot_wait.showGone()}
            loginHelper.onModifySuccess = {
                toast(R.string.modify_password_success,3000)
                onBackPresss()
            }
            loginHelper.onModifyFailed = {toast(R.string.modify_password_failed,3000)}
            loginHelper.modifyPassword(email,password)
        }
    }

    /**
     * 获取验证码
     */
    private fun getVerify() {
        val email = et_forgot_email.text.toString()
        if (email.isEmpty()) toast(R.string.email_is_empty, 3000)
        else {
            val loginHelper = LoginHelper(activity)
            loginHelper.onGetVerifySuccess = {toast(R.string.get_verify_success,3000)}
            loginHelper.onGetVerifyFailed = {toast(R.string.get_verify_failed,3000)}
            loginHelper.getVerify(email)
        }
       
    }

    override fun onBackPresss(): Boolean {
        toFrag(javaClass, Frag_login::class.java, null, true, 0)
        return true
    }

}
