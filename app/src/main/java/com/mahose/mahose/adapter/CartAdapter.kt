package com.mahose.mahose.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.bean.CartBean

/*
 * Created by 54484 on 11/11/2021.
 */
class CartAdapter(context: Context, cartBeans: ArrayList<CartBean>, subTotalSet: (Long) -> Unit) : RecyclerView.Adapter<CartHolder>() {

    var context: Context? = null
    var cartBeans: ArrayList<CartBean> = ArrayList()
    var subtotal: Long = 0 // 小计
    var onSubTotalCaculateListener: ((subtotal: Long) -> Unit)? = null // 总价回调

    init {
        this.context = context
        this.cartBeans = cartBeans
        subtotal = caculate() // 计算小计
        subTotalSet.invoke(subtotal)
    }

    fun notifys(cartBeans: ArrayList<CartBean>) {
        this.cartBeans = cartBeans
        subtotal = caculate() // 计算小计
        onSubTotalCaculateListener?.invoke(subtotal)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val cartBean = cartBeans[position]
        holder.iv_pic?.setImageBitmap(cartBean.pic)
        holder.tv_title?.text = cartBean.title
        holder.tv_subtitle?.text = cartBean.subtitle
        holder.tv_price?.text = "$" + cartBean.price * cartBean.count
        holder.tv_count?.text = cartBean.count.toString()
        holder.bt_minus?.setOnClickListener {
            if (cartBean.count <= 1) return@setOnClickListener
            cartBean.count -= 1
            holder.tv_count?.text = cartBean.count.toString()
            subtotal = caculate() // 计算小计
            onSubTotalCaculateListener?.invoke(subtotal)
            notifyDataSetChanged()
        }
        holder.bt_add?.setOnClickListener {
            cartBean.count += 1
            holder.tv_count?.text = cartBean.count.toString()
            subtotal = caculate() // 计算小计
            onSubTotalCaculateListener?.invoke(subtotal)
            notifyDataSetChanged()
        }
    }

    /**
     * 计算小计
     */
    private fun caculate(): Long {
        var total: Long = 0
        for (cartBean in cartBeans) {
            total += cartBean.price * cartBean.count
        }
        return total
    }

    override fun getItemCount(): Int {
        return cartBeans.size
    }
}
