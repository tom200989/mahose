package com.mahose.mahose.app

import android.text.TextUtils
import com.hiber.hiber.language.LangHelper
import com.hiber.hiber.language.RootApp
import com.hiber.tools.ShareUtils
import com.mahose.mahose.bean.Cons
import com.rootmastatic.rootmastatic.util.SPUtils

/*
 * Created by PD on 2021/10/8.
 */
class MaHoseApp : RootApp() {
    override fun onCreate() {
        super.onCreate()
        // 初始化语言
        cacheLanguage()
    }

    /**
     * 把需要展示的语言保存到缓存
     * 在[语言选择页]再取出来显示
     */
    private fun cacheLanguage() {
        val locale = LangHelper.getLocale(applicationContext)
        val language = locale.language
        val country = locale.country
        // 如果没有指定地区, 则格式为: es-default, 否则es-MX (一般情况下即便是从系统取, 系统会自动带地区, 这里仅仅为防止其他情况)
        SPUtils.get(this).putString(Cons.CURRENT_LANG, language + "-" + if (TextUtils.isEmpty(country)) "default" else country)
    }
}
