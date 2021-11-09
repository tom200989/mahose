package com.mahose.mahose.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R
import com.mahose.mahose.bean.ChatBean
import com.mahose.mahose.bean.ChatBean.CHAT_TYPE.*

/*
 * Created by 54484 on 11/8/2021.
 */
class ChatAdapter(context: Context, chatbeans: ArrayList<ChatBean>) : RecyclerView.Adapter<ChatHolder>() {

    var context: Context? = null
    var chatbeans: ArrayList<ChatBean> = ArrayList()
    var onPicClickListener: ((url: String) -> Unit)? = null

    init {
        this.context = context
        this.chatbeans = chatbeans
    }

    fun notifys(chatbeans: ArrayList<ChatBean>) {
        this.chatbeans = chatbeans
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val chatBean = chatbeans[position]
        // 客服
        holder.rl_kefu?.visibility = if (chatBean.isKefu) View.VISIBLE else View.GONE
        holder.iv_head?.setImageBitmap(chatBean.head_bitmap)
        holder.tv_content?.visibility = if (chatBean.type == TEXT) View.VISIBLE else View.GONE
        holder.tv_content?.text = chatBean.content
        holder.rl_pic?.visibility = if (chatBean.type == PIC) View.VISIBLE else View.GONE
        holder.rl_pic?.setOnClickListener { chatBean.pic_url?.let { url -> onPicClickListener?.invoke(url) } }
        holder.iv_pic?.setImageBitmap(chatBean.pic)
        holder.tv_time?.text = chatBean.time

        // 顾客
        holder.rl_guke?.visibility = if (chatBean.isKefu) View.GONE else View.VISIBLE
        holder.iv_head2?.setImageBitmap(chatBean.head_bitmap)
        holder.tv_content2?.visibility = if (chatBean.type == TEXT) View.VISIBLE else View.GONE
        holder.tv_content2?.text = chatBean.content
        holder.rl_pic2?.visibility = if (chatBean.type == PIC) View.VISIBLE else View.GONE
        holder.rl_pic2?.setOnClickListener { chatBean.pic_url?.let { url -> onPicClickListener?.invoke(url) } }
        holder.iv_pic2?.setImageBitmap(chatBean.pic)
        holder.tv_time2?.text = chatBean.time

    }

    override fun getItemCount(): Int {
        return chatbeans.size
    }
}
