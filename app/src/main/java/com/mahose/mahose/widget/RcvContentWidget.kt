package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/*
 * Created by 54484 on 10/20/2021.
 */
class RcvContentWidget : RecyclerView {

    var onScrollToBottomListener: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 增加滑动监听器
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 判断是否滚动到底部
                if (newState == SCROLL_STATE_IDLE && !canScrollVertically(1)) {
                    onScrollToBottomListener?.invoke()
                }
            }
        })
    }

    // 1.防止瀑布流的做法 - 重写滚动监听方法
    override fun addOnScrollListener(listener: OnScrollListener) {
        super.addOnScrollListener(listener)
        // 2.防止瀑布流的做法 - 判断是否为瀑布流布局
        if (layoutManager is StaggeredGridLayoutManager) {
            val staggerLayoutManager = (layoutManager as StaggeredGridLayoutManager)
            // 3.防止瀑布流的做法 - 防止第一元素到顶部有空白区域
            staggerLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            staggerLayoutManager.invalidateSpanAssignments()
        }
    }

}
