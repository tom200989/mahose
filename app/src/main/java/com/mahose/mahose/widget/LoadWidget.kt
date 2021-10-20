package com.mahose.mahose.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.mahose.mahose.R

/*
 * Created by 54484 on 10/20/2021.
 */
class LoadWidget : RelativeLayout {

    var iv_load: ImageView? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_load, this)
        inflate.findViewById<ImageView>(R.id.iv_load_bg).setOnClickListener(null)
        iv_load = inflate.findViewById(R.id.iv_load_circle)

    }

    fun showVisible() {
        visibility = View.VISIBLE // 显示
        setAnim() // 设置动画
    }

    fun showGone() {
        visibility = View.GONE // 隐藏
        stopAnim() // 停止动画
    }

    /**
     * 停止动画
     */
    private fun stopAnim() {
        if (iv_load!!.animation != null) {
            iv_load!!.animation.cancel()
            iv_load!!.clearAnimation()
        }
    }

    /**
     * 设置动画
     */
    private fun setAnim() {
        stopAnim() // 先停止上一次动画
        val rotate = RotateAnimation(0f, 360f, 1, 0.5f, 1, 0.5f)
        rotate.duration = 500
        rotate.fillAfter = true
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = Animation.INFINITE
        rotate.repeatMode = Animation.INFINITE
        iv_load!!.animation = rotate
        rotate.startNow()
        iv_load!!.startAnimation(rotate)
    }
}
