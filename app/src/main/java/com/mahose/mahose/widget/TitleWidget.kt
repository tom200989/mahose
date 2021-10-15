package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mahose.mahose.R
import com.mahose.mahose.helper.ClickHelper
import kotlinx.android.synthetic.main.widget_title.view.*

/*
 * Created by PD on 2021/10/13.
 */
class TitleWidget : RelativeLayout {

    var OnTitleClickListener: ((enum: TITLE_ENUM) -> Unit)? = null
    var OnDoubleClickListener: (() -> Unit)? = null

    var iv_search: ImageView? = null
    var iv_collect: ImageView? = null
    var tv_title: TextView? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_title, this)
        iv_search = inflate.findViewById(R.id.iv_search)
        iv_collect = inflate.findViewById(R.id.iv_collect)
        tv_title = inflate.findViewById(R.id.tv_title)
        iv_search?.setOnClickListener { OnTitleClickListener?.invoke(TITLE_ENUM.SEARCH) }
        iv_collect?.setOnClickListener { OnTitleClickListener?.invoke(TITLE_ENUM.COLLECT) }
        ClickHelper(rl_title, 2) { OnDoubleClickListener?.invoke() }.click()// 双击

    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        tv_title?.setText(title)
    }

    /**
     * 辅助切换
     */
    enum class TITLE_ENUM {
        SEARCH, COLLECT
    }
}
