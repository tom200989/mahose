package com.mahose.mahose.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.logma.logma.tool.Logma
import com.mahose.mahose.R

/*
 * Created by PD on 2021/10/12.
 */
class TabWidget : RelativeLayout {

    var onTabClickListener: ((tab_enum: TAB_ENUM) -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    @SuppressLint("UseCompatLoadingForDrawables")
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // 此处初始化你的业务逻辑
        val inflate = View.inflate(context, R.layout.widget_tab, this)
        val iv_video = inflate.findViewById<ImageView>(R.id.iv_tab_video)
        val iv_pic = inflate.findViewById<ImageView>(R.id.iv_tab_pic)
        val iv_setting = inflate.findViewById<ImageView>(R.id.iv_tab_setting)
        // 添加到集合
        val checks = arrayOf(R.drawable.video_check, R.drawable.pic_check, R.drawable.setting_check)
        val unchecks = arrayOf(R.drawable.video_uncheck, R.drawable.pic_uncheck, R.drawable.setting_uncheck)
        val enums = arrayOf(TAB_ENUM.VIDEO, TAB_ENUM.PIC, TAB_ENUM.SETTING)
        val ivs = arrayOf(iv_video, iv_pic, iv_setting)
        for (index in ivs.indices) {
            // 循环设置
            ivs[index].setOnClickListener {
                // 当点击时, 判断点击的是否为当前按钮
                for (j in ivs.indices) {
                    ivs[j].setImageDrawable(context.getDrawable(if (index == j) checks[j] else unchecks[j]))
                    ivs[j].alpha = if (index == j) 1.0f else 0.3f
                    if (index == j) {
                        // 回调
                            Logma.i("ma_tab","index = $index")
                        onTabClickListener?.invoke(enums[index])
                    }
                }

            }
        }
    }

    /**
     * 辅助切换
     */
    enum class TAB_ENUM(i: Int) {
        VIDEO(0), PIC(1), SETTING(2)
    }
}
