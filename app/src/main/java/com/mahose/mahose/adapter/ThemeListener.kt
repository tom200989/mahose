package com.mahose.mahose.adapter

import androidx.viewpager.widget.ViewPager

/*
 * Created by 54484 on 10/22/2021.
 */
open class ThemeListener : ViewPager.OnPageChangeListener {

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // position :  当前选定的位置
        // positionOffset:  当前滑动比例
        // positionOffsetPixels:  当前滑过的像素
    }

    override fun onPageSelected(position: Int) {
        // position :  当前选定的位置
    }

    override fun onPageScrollStateChanged(state: Int) {
        // state : 当前位于何种状态
    }
}
