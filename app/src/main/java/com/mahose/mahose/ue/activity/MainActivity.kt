package com.mahose.mahose.ue.activity

import androidx.core.view.LayoutInflaterCompat
import com.hiber.bean.RootProperty
import com.hiber.hiber.RootMAActivity
import com.ktapp.skin.SkinFactory
import com.mahose.mahose.R
import com.mahose.mahose.ue.frag.*
import com.mahose.mahose.utils.OtherUtils
import com.rootmastatic.rootmastatic.util.SPUtils
import java.io.File

class MainActivity : RootMAActivity() {

    // 用于记住在TAB切换时 - 切换后的页面类名
    var currentTag: String = ""

    // 页面数组
    var frags = arrayOf(
        Frag_splash::class.java, // 启动页
        Frag_video::class.java, // 视频页
        Frag_pic::class.java, // 图片页
        Frag_setting::class.java, // 设置页
        Frag_language::class.java, // 语言页
        Frag_theme::class.java, // 主题页
        Frag_temp::class.java, // 切换主题临时页
        Frag_search::class.java, // 搜索页
        Frag_privacy::class.java, // 隐私页
        Frag_cart::class.java, // 购物车页
        Frag_chat::class.java, // 客服页
        Frag_login::class.java, // 登录页
        Frag_registe::class.java, // 注册页
        Frag_forgot::class.java, // 忘记页
        Frag_like::class.java, // 收藏页
        Frag_test::class.java // 测试
    )

    override fun beforeAllFirst() {
        // 加载皮肤插件 todo 这一步从sp中读取 - 在设置页-主题页里进行保存
        var skinpath = getExternalFilesDir(null)?.absolutePath + File.separator + "/skin.apk"
        val themeInfo = OtherUtils.getThemeInfo(this)
        skinpath = themeInfo?.path ?: skinpath
        LayoutInflaterCompat.setFactory2(layoutInflater, SkinFactory(application, skinpath))
    }

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
