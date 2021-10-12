package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_video.*

/*
 * Created by PD on 2021/10/9.
 */
class Frag_video : RootFrag() {
    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        return R.layout.frag_video
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        tv_home.setOnClickListener {
            toFrag(javaClass, Frag_test::class.java, null, true, 0)
        }
    }

    override fun onBackPresss(): Boolean {
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) activity.wd_tab.visibility = View.GONE
    }

}
