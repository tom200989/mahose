package com.mahose.mahose.adapter

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 10/25/2021.
 */
class LanguageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var rl_language: RelativeLayout? = null // 总布局
    var tv_language_title: TextView? = null // 语言标题
    var rl_language_check: RelativeLayout? = null // 选中

    init {
        rl_language = itemView.findViewById(R.id.rl_item_language)
        tv_language_title = itemView.findViewById(R.id.tv_item_language)
        rl_language_check = itemView.findViewById(R.id.rl_item_language_check)
    }
}
