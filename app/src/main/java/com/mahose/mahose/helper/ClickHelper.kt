package com.mahose.mahose.helper

import android.os.SystemClock
import android.view.View


/*
 * Created by 54484 on 10/15/2021.
 */
class ClickHelper(var view:View, var n:Int,  val clickEvent: (() -> Unit)) {
    
    fun click() {
        val mHits = LongArray(n)
        view.setOnClickListener {
            // 参数说明:
            // 1.拷贝的原数组
            // 2.要拷贝的原数组的元素的位置
            // 3.拷贝到哪个目标数组
            // 4.要拷贝到目标数组的元素位置
            // 5.拷贝多少个元素
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] >= SystemClock.uptimeMillis() - 500) {
                // N击事件
                clickEvent()
            }
        }
    }
}
