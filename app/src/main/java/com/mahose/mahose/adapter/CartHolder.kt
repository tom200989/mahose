package com.mahose.mahose.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 11/11/2021.
 */
class CartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 普通条目
    var rl_each: RelativeLayout? = null // 总布局
    var iv_pic: ImageView? = null // 图片
    var tv_title: TextView? = null // 标题
    var tv_subtitle: TextView? = null // 副标题
    var tv_price: TextView? = null // 单价
    var bt_minus: Button? = null // 减号
    var tv_count: TextView? = null // 数量
    var bt_add: Button? = null // 加号

    // 小计条目
    var rl_subtotal: RelativeLayout? = null // 总布局
    var tv_subtotal: TextView? = null // 总计

    init {
        // 普通条目
        rl_each = itemView.findViewById(R.id.rl_item_cart_each)
        iv_pic = itemView.findViewById(R.id.iv_item_cart_pic)
        tv_title = itemView.findViewById(R.id.tv_item_cart_title)
        tv_subtitle = itemView.findViewById(R.id.tv_item_cart_subtitle)
        tv_price = itemView.findViewById(R.id.tv_item_cart_price)
        bt_minus = itemView.findViewById(R.id.bt_item_cart_minus)
        tv_count = itemView.findViewById(R.id.tv_item_cart_count)
        bt_add = itemView.findViewById(R.id.bt_item_cart_add)
        // 小计条目
        rl_subtotal = itemView.findViewById(R.id.rl_item_cart_subtotal)
        tv_subtotal = itemView.findViewById(R.id.tv_item_cart_subtotal_price)
    }

}
