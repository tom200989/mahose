package com.mahose.mahose.ue.frag

import android.view.View
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R

/*
 * Created by PD on 2021/10/8.
 */
class Frag_test : RootFrag() {
    
    override fun onInflateLayout(): Int {
        return R.layout.frag_test
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        
    }

    override fun onBackPresss(): Boolean {
        return false
    }
}
