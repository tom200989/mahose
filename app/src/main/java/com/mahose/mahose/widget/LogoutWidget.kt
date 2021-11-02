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
class LogoutWidget : RelativeLayout {

    var onLogoutClickOkListener: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_logout, this)
        // 背景
        val iv_bg = inflate.findViewById<Button>(R.id.iv_logout_bg)
        iv_bg.setOnClickListener(null)
        // OK按钮
        val btn_ok = inflate.findViewById<Button>(R.id.bt_logout_ok)
        btn_ok.setOnClickListener {
            visibility = View.GONE
            onLogoutClickOkListener?.invoke()
        }
        // 取消按钮
        val btn_cancel = inflate.findViewById<Button>(R.id.bt_logout_cancel)
        btn_cancel.setOnClickListener {
            visibility = View.GONE
        }
    }
}
