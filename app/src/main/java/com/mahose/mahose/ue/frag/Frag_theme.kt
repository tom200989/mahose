package com.mahose.mahose.ue.frag

import android.text.TextUtils
import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import com.mahose.mahose.adapter.ThemeAdapter
import com.mahose.mahose.adapter.ThemeListener
import com.mahose.mahose.bean.ThemeBean
import com.mahose.mahose.ue.activity.TempActivity
import com.mahose.mahose.utils.OtherUtils
import kotlinx.android.synthetic.main.frag_theme.*

/*
 * Created by 54484 on 10/22/2021.
 */
class Frag_theme : RootFrag() {

    var TAG = javaClass.simpleName
    var themes: ArrayList<ThemeBean>? = null
    var themeBean: ThemeBean? = null
    var last_index = 0
    var last_title = ""

    override fun onInflateLayout(): Int {
        return R.layout.frag_theme
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 获取上次选中的主题
        initData()
        // 初始化事件
        initListener()
    }

    /**
     * 获取上次选中的主题
     */
    private fun initData() {
        // 得到全部主题包信息
        themes = OtherUtils.getVirtualTheme(activity)
        // 读取上次主题选择
        val themeInfo = OtherUtils.getThemeInfo(activity)
        if (themeInfo != null) {
            last_index = themeInfo.index
            last_title = themeInfo.title ?: ""
        }

    }

    /**
     * 初始化事件
     */
    private fun initListener() {
        // 回退
        rl_theme_back.setOnClickListener { onBackPresss() }

        // 轮播图
        vp_theme.adapter = ThemeAdapter(themes!!)
        vp_theme.currentItem = last_index
        tv_theme_title.text = last_title
        vp_theme.addOnPageChangeListener(object : ThemeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 得到主题对象
                themeBean = themes!![position]
                // 切换标题
                tv_theme_title.text = themeBean!!.title
            }
        })

        // 点击确定
        bt_theme_confirm.setOnClickListener {
            if (TextUtils.isEmpty(themeBean!!.path)) {
                // 用户没有选择 - 回退
                onBackPresss()
            } else {
                // 保存到SP
                OtherUtils.saveThemeInfo(activity, themeBean!!)
                // 跳转到其他Activity
                toFragActivity(javaClass, TempActivity::class.java, Frag_temp::class.java, null, true, true, 0)
            }
        }
    }

    override fun onBackPresss(): Boolean {
        // 跳转会设置页
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
