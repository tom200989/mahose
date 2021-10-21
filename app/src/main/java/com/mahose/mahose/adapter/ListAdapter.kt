package com.mahose.mahose.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mahose.mahose.R
import com.mahose.mahose.bean.ListBean
import com.mahose.mahose.utils.OtherUtils
import kotlin.math.roundToInt

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

    // 4.防止瀑布流的做法 - 但还是有概率会出现重排序
    // (这一步一定要加, 且要在data集合中同时配置图片的大小便于计算)
    // 否则在rcv设置了invalidateSpanAssignments之后, 图片控件会变形
    // https://www.jianshu.com/p/e441c0362364
    override fun getItemViewType(position: Int): Int {
        val listBean = datas[position]
        val tw = listBean.thumb_width
        val th = listBean.thumb_height
        return (tw / th * 10f).roundToInt()
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val listBean = datas[position] // 数据

        // ** 加入该监听的目的是为了解决"图片宽度<屏幕一半"时产生的图片缩放变形问题
        holder.iv_main?.viewTreeObserver?.addOnPreDrawListener {
            // 获取图片宽高
            val thumbWidth = listBean.thumb_width
            val thumbHeight = listBean.thumb_height
            val ivWidth = holder.iv_main?.width
            // 如果图片<屏幕一半
            if (thumbWidth < ivWidth!! / 2) {
                val rate = ivWidth / thumbWidth
                // 则让图片控件按照屏图比进行放大以令图片比例不变形 - 否则在fitxy的类型下, 图片比例在高度上会变形
                holder.iv_main?.layoutParams?.height = (thumbHeight * rate).toInt()
            }
            // 其他设置
            holder.iv_main?.setImageBitmap(listBean.main_bitmap) // 竖图
            holder.iv_default?.visibility = if (listBean.main_bitmap == null) View.VISIBLE else View.GONE // 默认图
            holder.iv_like?.visibility = if (listBean.isLike) View.VISIBLE else View.GONE // 小心心
            holder.tv_title?.text = if (TextUtils.isEmpty(listBean.title)) context?.getString(R.string.something_wrong) else listBean.title // 标题
            holder.iv_head?.setImageBitmap(if (listBean.head_bitmap == null) OtherUtils.draw_to_bitmap(context!!, R.drawable.head_default) else listBean.head_bitmap) // 头像
            holder.tv_people?.text = String.format(context!!.getString(R.string.people_like), listBean.likeNum.toString()) // 喜欢人数

            true
        }
    }

    override fun getItemCount(): Int {
        return if (datas.isEmpty()) 0 else datas.size
    }
}
