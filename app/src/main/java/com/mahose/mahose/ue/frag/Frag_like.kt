package com.mahose.mahose.ue.frag

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.hiber.hiber.RootFrag
import com.mahose.mahose.R
import com.mahose.mahose.adapter.LikeAdapter
import com.mahose.mahose.bean.LikeBean
import com.mahose.mahose.bean.LikeBean.LIKE_TYPE.*
import com.mahose.mahose.utils.OtherUtils
import kotlinx.android.synthetic.main.frag_like.*

/*
 * Created by 54484 on 11/16/2021.
 */
class Frag_like : RootFrag() {

    var likes: ArrayList<LikeBean> = ArrayList()
    var likeAdapter: LikeAdapter? = null

    override fun onInflateLayout(): Int {
        return R.layout.frag_like
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {

        initData() // TODO: 11/16/2021  模拟数据(以后从网络读取)
        initAdapter()
        initEvent()
    }

    private fun initData() {
        likes = OtherUtils.getLikeInfo(activity)
    }

    private fun initAdapter() {
        likeAdapter = LikeAdapter(activity, likes)
        likeAdapter?.onClickLikeListener = { toDetail(it) }
        rcv_like.adapter = likeAdapter
        rcv_like.layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        rcv_like.setHasFixedSize(true)
        rcv_like.isNestedScrollingEnabled = false
    }

    /**
     * 跳转到详情页
     */
    private fun toDetail(likeBean: LikeBean) {
        val type = likeBean.type
        when (type) {
            UNKNOWN -> toast(getString(R.string.do_not_click_fast), 3000)
            PIC -> {
                // TODO: 11/16/2021  跳转到图片详情页
                toast("to pic", 2000)
            }
            VIDEO -> {
                // TODO: 11/16/2021  跳转到视频详情页
                toast("to video", 2000)
            }
        }

    }

    private fun initEvent() {
        // 回退
        rl_like_back.setOnClickListener { onBackPresss() }
    }

    override fun onBackPresss(): Boolean {
        // 返回上一页
        toFrag(javaClass, lastFrag, null, true, 0)
        return true
    }

}
