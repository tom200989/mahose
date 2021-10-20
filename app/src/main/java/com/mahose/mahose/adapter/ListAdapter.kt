package com.mahose.mahose.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mahose.mahose.R
import com.mahose.mahose.bean.ListBean
import com.mahose.mahose.utils.OtherUtils

/*
 * Created by 54484 on 10/19/2021.
 */
class ListAdapter(context: Context, datas: ArrayList<ListBean>) : Adapter<ListHolder>() {

    var context: Context? = null
    var datas: ArrayList<ListBean> = ArrayList()

    init {
        this.datas = datas
        this.context = context
    }

    fun notifys(datas: ArrayList<ListBean>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val listBean = datas[position] // 数据
        holder.iv_main?.setImageBitmap(listBean.main_bitmap) // 竖图
        holder.iv_default?.visibility = if (listBean.main_bitmap == null) View.VISIBLE else View.GONE // 默认图
        holder.iv_like?.visibility = if (listBean.isLike) View.VISIBLE else View.GONE // 小心心
        holder.tv_title?.text = if (TextUtils.isEmpty(listBean.title)) context?.getString(R.string.something_wrong) else listBean.title // 标题
        holder.iv_head?.setImageBitmap(if (listBean.head_bitmap == null) OtherUtils.draw_to_bitmap(context!!, R.drawable.head_default) else listBean.head_bitmap) // 头像
        holder.tv_people?.text = String.format(context!!.getString(R.string.people_like), listBean.likeNum.toString()) // 喜欢人数
    }

    override fun getItemCount(): Int {
        return if (datas.isEmpty()) 0 else datas.size
    }
}
