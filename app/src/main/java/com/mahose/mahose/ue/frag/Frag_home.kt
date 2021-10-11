package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R

/*
 * Created by PD on 2021/10/9.
 */
class Frag_home:RootFrag() {
    override fun onInflateLayout(): Int {
        return R.layout.frag_home
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        TODO("Not yet implemented")
    }

    override fun onBackPresss(): Boolean {
        return true
    }
}
