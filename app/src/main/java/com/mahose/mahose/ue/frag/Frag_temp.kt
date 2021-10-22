package com.mahose.mahose.ue.frag

import android.os.Handler
import android.os.Looper
import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import com.mahose.mahose.ue.activity.MainActivity
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other
import kotlinx.android.synthetic.main.frag_temp.*

/*
 * Created by 54484 on 10/22/2021.
 */
class Frag_temp : RootFrag() {
    override fun onInflateLayout(): Int {
        return R.layout.frag_temp
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 设置动画
        setAnim()
        // 跳转回主页
        toBackSplash()
    }

    private fun setAnim() {
        OtherUtils.setRandomTransAnim(iv_temp_1)
        OtherUtils.setRandomTransAnim(iv_temp_2)
        OtherUtils.setRandomTransAnim(iv_temp_3)
        OtherUtils.setRandomTransAnim(iv_temp_4)
    }

    private fun toBackSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            // 跳转回主页
            toFragActivity(javaClass, MainActivity::class.java, Frag_splash::class.java, null, true, true, 0)
        }, 2000)
    }

    override fun onBackPresss(): Boolean {
        return true
    }
}
