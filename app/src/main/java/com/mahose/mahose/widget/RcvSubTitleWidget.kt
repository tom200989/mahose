package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.frag_pic.*

/*
 * Created by 54484 on 10/20/2021.
 */
class RcvSubTitleWidget : RecyclerView {
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    override fun addOnScrollListener(listener: OnScrollListener) {
        super.addOnScrollListener(listener)
    }

    override fun smoothScrollToPosition(position: Int) {
        super.smoothScrollToPosition(position)
        // 获取当前点击的item位置位移
        val itemWidth = getChildAt(0).width
        // 获取rcv的中心
        val rcv_half_w = width / 2
        // 如果当前点击的item不在recycleview的中心点 - 则把滚动rcv以令当前item移动到该中心位置
        if (layoutManager is LinearLayoutManager) (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, rcv_half_w - itemWidth / 2)
    }
}
