package com.mahose.mahose.ue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiber.bean.RootProperty
import com.hiber.hiber.BuildConfig
import com.hiber.hiber.RootMAActivity
import com.mahose.mahose.R
import com.mahose.mahose.ue.frag.Frag_test

class MainActivity : RootMAActivity() {

    var frags = arrayOf(
        Frag_test::class.java // 测试
    )


    override fun initProperty(): RootProperty {
        val property = RootProperty()
        property.colorStatusBar = R.color.theme_color
        property.layoutId = R.layout.activity_main
        property.containId = R.id.fl_contain
        property.fragmentClazzs = frags
        property.isFullScreen = true
        property.isSaveInstanceState = false
        property.packageName = "com.mahose.mahose"
        return property
    }

    override fun onNexts() {

    }

    override fun onBackClick(): Boolean {
        return false
    }
}
