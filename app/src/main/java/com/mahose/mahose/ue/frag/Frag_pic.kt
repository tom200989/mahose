package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import kotlinx.android.synthetic.main.activity_main.*

/*
 * Created by PD on 2021/10/9.
 */
class Frag_pic : RootFrag() {
    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        activity.wd_title.visibility = View.VISIBLE
        return R.layout.frag_pic
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onBackPresss(): Boolean {
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) activity.wd_tab.visibility = View.GONE
        if (hidden) activity.wd_title.visibility = View.GONE
    }
}
