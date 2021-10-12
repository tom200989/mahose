package com.mahose.mahose.ue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiber.bean.RootProperty
import com.hiber.hiber.RootMAActivity
import com.logma.logma.tool.Logma
import com.mahose.mahose.BuildConfig
import com.mahose.mahose.R
import com.mahose.mahose.ue.frag.*
import com.mahose.mahose.widget.TabWidget

class MainActivity : RootMAActivity() {

    var frags = arrayOf(
        Frag_splash::class.java, // 启动页
        Frag_video::class.java, // 视频页
        Frag_pic::class.java, // 图片页
        Frag_setting::class.java, // 设置页
        Frag_test::class.java // 测试
    )

    var wd_tab: TabWidget? = null

    override fun initProperty(): RootProperty {
        val property = RootProperty()
        property.colorStatusBar = R.color.theme_color
        property.layoutId = R.layout.activity_main
        property.containId = R.id.fl_contain
        property.fragmentClazzs = frags
        property.isFullScreen = true
        property.isSaveInstanceState = false
        property.packageName = BuildConfig.APPLICATION_ID
        return property
    }

    override fun onNexts() {
        wd_tab = findViewById(R.id.wd_tab)
    }

    override fun onBackClick(): Boolean {
        return false
    }
}
