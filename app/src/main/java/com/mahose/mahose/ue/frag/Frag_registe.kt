package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
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
        // TODO: 11/4/2021  注册的逻辑
    }

    override fun onBackPresss(): Boolean {
        // 回退到上一级
        toFrag(javaClass, lastFrag, null, true, 0)
        return true
    }
}
