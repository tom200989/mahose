package com.mahose.mahose.adapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R

/*
 * Created by 54484 on 11/16/2021.
 */
class LikeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var iv_like: ImageView? = null

    init {
        iv_like = itemView.findViewById(R.id.iv_item_like)
    }


}
