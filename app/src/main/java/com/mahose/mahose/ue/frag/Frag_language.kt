package com.mahose.mahose.ue.frag

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiber.hiber.RootFrag
import com.hiber.hiber.language.LangHelper
import com.hiber.tools.ShareUtils
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.adapter.LanguageAdapter
import com.mahose.mahose.bean.Cons
import com.mahose.mahose.bean.LanguageBean
import com.rootmastatic.rootmastatic.util.SPUtils
import kotlinx.android.synthetic.main.frag_language.*
import java.util.*
import kotlin.collections.ArrayList

/*
 * Created by 54484 on 10/25/2021.
 */
class Frag_language : RootFrag() {
    val TAG = javaClass.simpleName
    var languages: ArrayList<LanguageBean>? = null


    override fun onInflateLayout(): Int {
        return R.layout.frag_language
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化语言
        languages = initLanguage()
        // 初始化适配器
        initAdapter()
        // 初始化事件
        initListener()
    }

    private fun initListener() {
        // 回退
        rl_language_back.setOnClickListener { onBackPresss() }
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        rcv_language.layoutManager = LinearLayoutManager(activity)
        val adapter = LanguageAdapter(activity, languages!!)
        adapter.onSwitchLanguageListener = { lang_coun ->
            // 切换语言
            transferLanguage(lang_coun)
        }
        rcv_language.adapter = adapter
    }

    /**
     * 切换语言
     */
    private fun transferLanguage(lang_coun: String) {
        val split = lang_coun.split("-")
        val language = split[0]
        var country = ""
        country = split[1]
        country = if (TextUtils.isEmpty(country) or (country == "default")) "" else country
        LangHelper.transfer(activity, language, country)
        SPUtils.get(activity).putString(Cons.CURRENT_LANG, language + "-" + if (TextUtils.isEmpty(country)) "default" else country)
        activity.recreate()
    }

    /**
     * 初始化语言
     */
    private fun initLanguage(): ArrayList<LanguageBean> {
        val language_country = SPUtils.get(activity).getString(Cons.CURRENT_LANG, "")
        Logma.i(TAG,"lang = $language_country")
        val languages: ArrayList<LanguageBean> = ArrayList()
        for (lang_coun in Cons.COUNTRYS) {
            val languageBean = LanguageBean()
            languageBean.lang_coun = lang_coun
            languageBean.displayName = getLanguageName(lang_coun)
            languageBean.isCheck = language_country?.contains(lang_coun) ?: false
            languages.add(languageBean)
        }
        return languages
    }

    /**
     * 获取语言展示名
     */
    private fun getLanguageName(lang_coun: String): String {
        var display = ""
        val split = lang_coun.split("-")
        if (split.size > 1) {
            val language = split[0]
            val country = split[1]
            val locale: Locale
            if (TextUtils.isEmpty(country) or (country == "default")) {
                locale = Locale(language)
            } else {
                locale = Locale(language, country)
            }
            display = locale.getDisplayName(locale)
        } else {
            val language = split[0]
            val locale = Locale(language)
            display = locale.getDisplayName(locale)
        }
        val chars = display.toCharArray()
        chars[0] = chars[0].uppercaseChar()
        return String(chars)
    }

    override fun onBackPresss(): Boolean {
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
