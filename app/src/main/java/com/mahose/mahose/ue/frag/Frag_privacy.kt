package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import kotlinx.android.synthetic.main.frag_privacy.*

/*
 * Created by 54484 on 10/27/2021.
 */
class Frag_privacy : RootFrag() {
    override fun onInflateLayout(): Int {
        return R.layout.frag_privacy
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initListener()
        initData()
    }

    private fun initData() {
        // TODO: 10/27/2021 读取sdcard的隐私条款内容
    }

    private fun initListener() {
        // 回退
        rl_privacy_back.setOnClickListener { onBackPresss() }
    }

    override fun onBackPresss(): Boolean {
        // 返回到设置页
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
