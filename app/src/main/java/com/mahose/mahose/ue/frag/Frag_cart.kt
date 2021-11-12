package com.mahose.mahose.ue.frag

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiber.hiber.RootFrag
import com.hiber.hiber.language.RootApp
import com.mahose.mahose.R
import com.mahose.mahose.adapter.CartAdapter
import com.mahose.mahose.bean.CartBean
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other
import kotlinx.android.synthetic.main.frag_cart.*

/*
 * Created by 54484 on 10/27/2021.
 */
class Frag_cart : RootFrag() {

    var cartBeans: ArrayList<CartBean> = ArrayList()
    var cartAdapter: CartAdapter? = null

    override fun onInflateLayout(): Int {
        return R.layout.frag_cart
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initData()
        initAdapter()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // TODO: 11/12/2021  模拟数据 - 以后更换为数据库获取
        cartBeans = OtherUtils.getCartInfo(activity)
        // TODO: 11/12/2021  还有提交面板没做, 下周做
    }

    private fun initAdapter() {
        cartAdapter = CartAdapter(activity, cartBeans)
        rcv_cart_order.layoutManager = LinearLayoutManager(activity)
        rcv_cart_order.adapter = cartAdapter
    }

    override fun onBackPresss(): Boolean {
        // 返回设置页
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
