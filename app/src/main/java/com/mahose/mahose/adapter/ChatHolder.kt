package com.mahose.mahose.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 11/8/2021.
 */
class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 客服
    var rl_kefu: RelativeLayout? = null
    var iv_head: ImageView? = null
    var tv_content: TextView? = null
    var rl_pic: RelativeLayout? = null
    var iv_pic: ImageView? = null
    var tv_time: TextView? = null
    
    // 顾客
    var rl_guke: RelativeLayout? = null
    var iv_head2: ImageView? = null
    var tv_content2: TextView? = null
    var rl_pic2: RelativeLayout? = null
    var iv_pic2: ImageView? = null
    var tv_time2: TextView? = null
    
    init{
        rl_kefu = itemView.findViewById(R.id.rl_item_chat_kefu)
        iv_head = itemView.findViewById(R.id.iv_item_chat_head)
        tv_content = itemView.findViewById(R.id.tv_item_chat_content)
        rl_pic = itemView.findViewById(R.id.rl_item_chat_pic)
        iv_pic = itemView.findViewById(R.id.iv_item_chat_pic)
        tv_time = itemView.findViewById(R.id.tv_item_chat_time)

        rl_guke= itemView.findViewById(R.id.rl_item_chat_guke)
        iv_head2 = itemView.findViewById(R.id.iv_item_chat_head2)
        tv_content2 = itemView.findViewById(R.id.tv_item_chat_content2)
        rl_pic2 = itemView.findViewById(R.id.rl_item_chat_pic2)
        iv_pic2 = itemView.findViewById(R.id.iv_item_chat_pic2)
        tv_time2 = itemView.findViewById(R.id.tv_item_chat_time2)
    }
}
