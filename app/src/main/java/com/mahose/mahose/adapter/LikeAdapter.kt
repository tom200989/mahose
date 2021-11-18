package com.mahose.mahose.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R
import com.mahose.mahose.bean.LikeBean

/*
 * Created by 54484 on 11/16/2021.
 */
class LikeAdapter(context: Context, likes: ArrayList<LikeBean>) : RecyclerView.Adapter<LikeHolder>() {

    var context: Context? = null
    var likes: ArrayList<LikeBean> = ArrayList()
    var onClickLikeListener: ((likeBean: LikeBean) -> Unit)? = null

    init {
        this.context = context
        this.likes = likes
    }

    fun notifys(likes: ArrayList<LikeBean>) {
        this.likes = likes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeHolder {
        return LikeHolder(LayoutInflater.from(context).inflate(R.layout.item_like, parent, false))
    }

    override fun onBindViewHolder(holder: LikeHolder, position: Int) {
        val likeBean = likes[position]
        holder.iv_like?.setImageBitmap(likeBean.thumb)
        holder.iv_like?.setOnClickListener {
            onClickLikeListener?.invoke(likeBean)
        }
    }

    override fun getItemCount(): Int {
        return likes.size
    }

}
