package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mahose.mahose.R

/*
 * Created by 54484 on 11/15/2021.
 */
class PayWidget : RelativeLayout {

    var inflate: View? = null
    var onClickPayOKListener: (() -> Unit)? = null
    var contexts: Context? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        this.contexts = context
        inflate = View.inflate(context, R.layout.widget_pay, this)
        inflate?.findViewById<ImageView>(R.id.iv_pay_bg)?.setOnClickListener(null)
        val btn_ok = inflate?.findViewById<Button>(R.id.bt_pay)
        btn_ok?.setOnClickListener {
            visibility = GONE
            onClickPayOKListener?.invoke()
        }
    }

    fun showSuccess() {
        // 设置图标
        val iv_icon = inflate?.findViewById<ImageView>(R.id.iv_pay_icon)
        iv_icon?.setImageDrawable(ContextCompat.getDrawable(contexts!!, R.drawable.pay_success))
        // 设置描述文本
        val tv_des = inflate?.findViewById<TextView>(R.id.tv_pay_des)
        tv_des?.text = contexts?.getString(R.string.pay_success)
        // 显示
        visibility = VISIBLE
    }

    fun showFailed() {
        // 设置图标
        val iv_icon = inflate?.findViewById<ImageView>(R.id.iv_pay_icon)
        iv_icon?.setImageDrawable(ContextCompat.getDrawable(contexts!!, R.drawable.pay_failed))
        // 设置描述文本
        val tv_des = inflate?.findViewById<TextView>(R.id.tv_pay_des)
        tv_des?.text = contexts?.getString(R.string.pay_failed)
        // 显示
        visibility = VISIBLE
    }

    fun showGone() {
        visibility = GONE
    }
}
