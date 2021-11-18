package com.mahose.mahose.ue.frag

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiber.hiber.RootFrag
import com.hiber.hiber.language.RootApp
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.adapter.CartAdapter
import com.mahose.mahose.bean.CartBean
import com.mahose.mahose.helper.PayHelper
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other
import kotlinx.android.synthetic.main.frag_cart.*

/*
 * Created by 54484 on 10/27/2021.
 */
class Frag_cart : RootFrag() {

    var cartBeans: ArrayList<CartBean> = ArrayList()
    var cartAdapter: CartAdapter? = null
    var isPaySuccess = false // 是否付款成功, 用于付款后判断

    override fun onInflateLayout(): Int {
        return R.layout.frag_cart
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        initData()
        initAdapter()
        initListener()
    }

    /**
     * 初始化监听
     */
    private fun initListener() {
        // 提交付款请求
        tv_cart_pay.setOnClickListener { toPay() }
        // 点击OK - 跳转到setting
        wd_pay.onClickPayOKListener = {
            if (isPaySuccess) // 如付款成功 - 跳回设置页 - 否则不跳转
                toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        }
    }

    /**
     * 去付款
     */
    private fun toPay() {
        val payHelper = PayHelper()
        payHelper.onPrepareListener = { wd_cart_load.showVisible() }
        payHelper.onEndListener = { wd_cart_load.showGone() }
        payHelper.onPaySuccess = { // 付款成功界面
            isPaySuccess = true
            wd_pay.showSuccess()
        }
        payHelper.onPayFailed = {// 付款失败界面
            isPaySuccess = false
            wd_pay.showFailed()
        }
        payHelper.pay(activity)
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // TODO: 11/12/2021  模拟数据 - 以后更换为数据库获取
        cartBeans = OtherUtils.getCartInfo(activity)
    }

    @SuppressLint("SetTextI18n")
    private fun initAdapter() {
        cartAdapter = CartAdapter(activity, cartBeans) { subTotal -> tv_cart_subtotal.text = "$$subTotal" }
        cartAdapter?.onSubTotalCaculateListener = { subTotal -> tv_cart_subtotal.text = "$$subTotal" }
        rcv_cart_order.layoutManager = LinearLayoutManager(activity)
        rcv_cart_order.adapter = cartAdapter
    }

    override fun onBackPresss(): Boolean {
        // 等待圈
        if (wd_cart_load.visibility == View.VISIBLE) return true
        // 返回设置页
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
