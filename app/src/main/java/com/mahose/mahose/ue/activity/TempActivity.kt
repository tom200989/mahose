package com.mahose.mahose.ue.activity

import android.os.Handler
import android.os.Looper
import com.hiber.bean.RootProperty
import com.hiber.hiber.RootMAActivity
import com.mahose.mahose.R
import com.mahose.mahose.ue.frag.*

/*
 * Created by 54484 on 10/22/2021.
 */
class TempActivity : RootMAActivity() {

    var frags = arrayOf(
        Frag_temp::class.java, // 过度页
    )

    override fun initProperty(): RootProperty {
        val property = RootProperty()
        property.colorStatusBar = R.color.theme_color
        property.layoutId = R.layout.activity_temp
        property.containId = R.id.tl_contain
        property.fragmentClazzs = frags
        property.isFullScreen = true
        property.isSaveInstanceState = false
        property.packageName = "com.mahose.mahose"
        return property
    }

    override fun onNexts() {
    }

    override fun onBackClick(): Boolean {
        return true
    }
}
