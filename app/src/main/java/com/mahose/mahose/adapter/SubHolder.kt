package com.mahose.mahose.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 10/19/2021.
 */
class SubHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    
    var tv_sub_title: TextView? = null // 副标题
    
    init {
        tv_sub_title = itemView.findViewById(R.id.tv_item_subtitle)
    }
}
