package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.mahose.mahose.R

/*
 * Created by PD on 2021/10/13.
 */
class TipWidget : RelativeLayout {


    var onTipClickOkListener: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_tip, this)
        val btn_ok = inflate.findViewById<Button>(R.id.bt_update_ok)
        btn_ok.setOnClickListener {
            visibility = View.GONE
            onTipClickOkListener?.invoke()
        }

    }
}
