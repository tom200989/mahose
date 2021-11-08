package com.mahose.mahose.ue.frag

import android.text.TextUtils
import android.view.View
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.bean.HoseCons
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other
import com.rootmastatic.rootmastatic.util.SPUtils
import kotlinx.android.synthetic.main.frag_search.*

/*
 * Created by 54484 on 10/26/2021.
 */
class Frag_search : RootFrag() {

    // 历史记录
    var contents: ArrayList<String> = ArrayList()

    override fun onInflateLayout(): Int {
        return R.layout.frag_search
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化数据
        initData()
        // 初始化视图
        initView()
        // 初始化事件
        initListener()
    }

    private fun initView() {
        // 历史数据设置
        tv_search_des.visibility = if (contents.isEmpty()) View.GONE else View.VISIBLE
        wd_flow.visibility = if (contents.isEmpty()) View.GONE else View.VISIBLE
        wd_flow.setDefault(contents)
        // 显示键盘
        OtherUtils.setKeyboard(et_search, activity, true)
    }

    private fun initListener() {
        // 回退
        rl_search_back.setOnClickListener { onBackPresss() }
        // 历史记录
        wd_flow.onClickSearchItemListener = { item_text ->
            // 隐藏键盘
            OtherUtils.setKeyboard(et_search, activity, false)
            // 搜索
            toSearch(item_text)
        }
        // Go!
        tv_search_go.setOnClickListener {
            val content = et_search.text.toString()
            if (TextUtils.isEmpty(content)) {
                toast(getString(R.string.please_input_what_you_want), 3000)
                return@setOnClickListener
            } else {
                // 先取原来的记录
                val was = SPUtils.get(activity).getString(HoseCons.SEARCH_WAS, "")
                // 拼接后再存入缓存
                SPUtils.get(activity).putString(HoseCons.SEARCH_WAS, content + HoseCons.SPLIT + was)
                // 隐藏键盘
                OtherUtils.setKeyboard(et_search, activity, false)
                // 清空区域
                et_search.setText("")
                // 加入当前集合
                contents.add(content)
                // 刷新布局
                wd_flow.setDefault(contents)
                // 搜索
                toSearch(content)
            }
        }
    }

    /**
     * 去搜索
     */
    fun toSearch(content: String) {
        // TODO: 10/26/2021 去搜索
    }

    /**
     * 模拟数据
     */
    private fun initData() {
        // 设置模拟数据
        contents = OtherUtils.searchs
        // 读取缓存数据
        val split = SPUtils.get(activity).getString(HoseCons.SEARCH_WAS, "")!!.split(HoseCons.SPLIT)
        for (content in split) {
            if (contents.contains(content) || content.isEmpty()) {
                continue
            } else {
                contents.add(content)
            }
        }
    }

    override fun onBackPresss(): Boolean {
        // 回退到Lastfrag
        OtherUtils.setKeyboard(et_search, activity, false)
        toFrag(javaClass, lastFrag, null, false, 0)
        return true
    }
}
