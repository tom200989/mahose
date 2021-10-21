package com.mahose.mahose.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mahose.mahose.R
import com.mahose.mahose.bean.SubBean
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other

/*
 * Created by 54484 on 10/19/2021.
 */
class SubAdapter(context: Context, datas: ArrayList<SubBean>) : Adapter<SubHolder>() {

    var context: Context? = null
    var datas: ArrayList<SubBean> = ArrayList()
    var onItemClickListener: ((position: Int, subTitle: String) -> Unit)? = null
    var tmp_positon = 0

    init {
        this.datas = datas
        this.context = context
    }

    fun notifys(datas: ArrayList<SubBean>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubHolder {
        return SubHolder(LayoutInflater.from(context).inflate(R.layout.item_subtitle, parent, false))
    }

    override fun onBindViewHolder(holder: SubHolder, position: Int) {
        // 颜色资源
        val black_color = ContextCompat.getColor(context!!, R.color.black_color)
        val dark_gray_color = ContextCompat.getColor(context!!, R.color.dark_gray_color)

        val subBean = datas[position] // 数据
        holder.tv_sub_title?.text = if (TextUtils.isEmpty(subBean.subTitle)) "..." else subBean.subTitle // 副标题
        holder.tv_sub_title?.setTextColor(if (subBean.isCheck) black_color else dark_gray_color)
        holder.tv_sub_title?.setOnClickListener {
            // 切换选中色
            setCheck(datas, position)
            // 回调
            if (position != tmp_positon) {
                onItemClickListener?.invoke(position, subBean.subTitle ?: "...")
                tmp_positon = position
            }
            // 刷新数据
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (datas.isEmpty()) 0 else datas.size
    }

    /**
     * 切换选中副标题样式
     * @param datas 原数据
     * @param position 当前选中索引
     */
    fun setCheck(datas: ArrayList<SubBean>, position: Int) {
        for (idx in datas.indices) {
            datas[idx].isCheck = idx == position
        }
    }
}
