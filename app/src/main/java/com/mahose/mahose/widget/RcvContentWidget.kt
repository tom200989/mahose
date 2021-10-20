package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/*
 * Created by 54484 on 10/20/2021.
 */
class RcvContentWidget : RecyclerView {
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    override fun addOnScrollListener(listener: OnScrollListener) {
        super.addOnScrollListener(listener)
        if (layoutManager is StaggeredGridLayoutManager) (layoutManager as StaggeredGridLayoutManager).invalidateSpanAssignments()
    }
}
