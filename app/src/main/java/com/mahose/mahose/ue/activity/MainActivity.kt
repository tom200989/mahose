package com.mahose.mahose.ue.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hiber.bean.RootProperty
import com.hiber.hiber.RootMAActivity
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.ue.frag.*
import com.mahose.mahose.widget.TabWidget

class MainActivity : RootMAActivity() {

    // 用于记住在TAB切换时 - 切换后的页面类名
    var currentTag: String = ""
    // 页面数组
    var frags = arrayOf(
        Frag_splash::class.java, // 启动页
        Frag_video::class.java, // 视频页
        Frag_pic::class.java, // 图片页
        Frag_setting::class.java, // 设置页
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
