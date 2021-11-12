package com.mahose.mahose.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R
import com.mahose.mahose.bean.CartBean

/*
 * Created by 54484 on 11/11/2021.
 */
class CartAdapter(context: Context, cartBeans: ArrayList<CartBean>) : RecyclerView.Adapter<CartHolder>() {

    var context: Context? = null
    var cartBeans: ArrayList<CartBean> = ArrayList()
    var subtotal: Long = 0 // 小计

    init {
        this.context = context
        this.cartBeans = cartBeans
        // 添加小计到末尾
        addSubtotal(cartBeans)
    }

    /**
     * 添加小计到末尾
     */
    private fun addSubtotal(cartBeans: ArrayList<CartBean>) {
        subtotal = caculate() // 计算小计
        val cartBean = CartBean() // 添加小计item到末尾
        cartBean.type = CartBean.TYPE.SUBTOTAL
        cartBeans.add(cartBean)
    }

    fun notifys(cartBeans: ArrayList<CartBean>) {
        this.cartBeans = cartBeans
        addSubtotal(cartBeans) // 添加小计到末尾
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val cartBean = cartBeans[position]
        // 普通条目
        holder.rl_each?.visibility = if (cartBean.type == CartBean.TYPE.NORMAL) View.VISIBLE else View.GONE
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
            notifyDataSetChanged()
        }
        holder.bt_add?.setOnClickListener {
            cartBean.count += 1
            holder.tv_count?.text = cartBean.count.toString()
            subtotal = caculate() // 计算小计
            notifyDataSetChanged()
        }
        // 小计条目
        holder.rl_subtotal?.visibility = if (cartBean.type == CartBean.TYPE.SUBTOTAL) View.VISIBLE else View.GONE
        holder.tv_subtotal?.text = "$$subtotal"
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
