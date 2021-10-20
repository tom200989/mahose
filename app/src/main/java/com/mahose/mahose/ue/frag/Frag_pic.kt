package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.ue.activity.MainActivity
import com.mahose.mahose.widget.TitleWidget
import kotlinx.android.synthetic.main.activity_main.*

/*
 * Created by PD on 2021/10/9.
 */
class Frag_pic : RootFrag() {
    var TAG = javaClass.simpleName
    
    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        activity.wd_title.visibility = View.VISIBLE
        (activity as MainActivity).currentTag = javaClass.simpleName
        return R.layout.frag_pic
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initListener()
    }

    /**
     * 初始化点击
     */
    private fun initListener() {
        /* 双击了标题区域 */
        activity.wd_title.OnDoubleClickListener = {
            // TODO: 10/15/2021  刷新数据
        }
        /* Title点击 */
        activity.wd_title.OnTitleClickListener = { enum ->
            Logma.i(TAG, "点击了 ${enum.name} 按钮")
            when (enum) {
                TitleWidget.TITLE_ENUM.SEARCH -> {
                    // TODO: 2021/10/13  点击了搜索按钮
                }
                TitleWidget.TITLE_ENUM.COLLECT -> {
                    // TODO: 2021/10/13  点击了收藏按钮
                }
            }
        }
    }

    override fun onBackPresss(): Boolean {
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) activity.wd_tab.visibility = View.GONE
        if (hidden) activity.wd_title.visibility = View.GONE
        if (hidden) (activity as MainActivity).currentTag = ""
    }
}
