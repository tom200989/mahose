package com.mahose.mahose.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 10/19/2021.
 */
class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    
    var iv_main: ImageView? = null // 竖图
    var iv_default: ImageView? = null // 默认
    var iv_like: ImageView? = null // 小心心
    var tv_title: TextView? = null // 标题
    var iv_head: ImageView? = null // 头像
    var tv_people: TextView? = null // 喜欢人数
    
    init {
         iv_main = itemView.findViewById(R.id.iv_item_list_verticle)
         iv_default = itemView.findViewById(R.id.iv_item_default)
         iv_like = itemView.findViewById(R.id.iv_item_like)
         tv_title = itemView.findViewById(R.id.tv_item_title)
         iv_head = itemView.findViewById(R.id.iv_item_head)
         tv_people = itemView.findViewById(R.id.tv_item_people_like)
    }
}
