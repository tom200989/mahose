package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mahose.mahose.R

/*
 * Created by 54484 on 10/20/2021.
 */
class ErrorWidget : RelativeLayout {

    var onTryAaginListener: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_error, this)
        inflate.findViewById<ImageView>(R.id.iv_error_bg).setOnClickListener { visibility = GONE }
        val tv_try_again = inflate.findViewById<TextView>(R.id.tv_error_try_again)
        tv_try_again.setOnClickListener {
            visibility = GONE
            onTryAaginListener?.invoke()
        }
    }
    
    fun showVisible(){
        visibility = VISIBLE
    }
    
    fun showGone(){
        visibility = GONE
    }
}
