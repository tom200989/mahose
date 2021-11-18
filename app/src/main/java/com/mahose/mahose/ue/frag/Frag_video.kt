package com.mahose.mahose.ue.frag

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.adapter.ListAdapter
import com.mahose.mahose.bean.ListBean
import com.mahose.mahose.helper.LoadHelper
import com.mahose.mahose.ue.activity.MainActivity
import com.mahose.mahose.widget.TitleWidget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_video.*


/*
 * 注意: 该列表所有的图片需要遵守以下原则
 * 从数据库读取的图片必须满足2:1或者1:2的配比
 * 即美工需要根据这个比例进行裁切, 如1000:500(竖向)、500:1000(横向)
 */
class Frag_video : RootFrag() {
    var TAG = javaClass.simpleName
    var listAdapter: ListAdapter? = null
    var datas: ArrayList<ListBean> = ArrayList()
    var loadHelper: LoadHelper? = null

    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        activity.wd_title.visibility = View.VISIBLE
        (activity as MainActivity).currentTag = javaClass.simpleName
        return R.layout.frag_video
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 如果是搜索页面回退的 - 则不重新加载
        if (!p2!!.contains(Frag_search::class.java.simpleName)) {
            // 初始化点击
            initListener()
            // 初始化列表
            initAdapter()
            // 加载数据 (要放在所有的控件初始化最后 - 未来可以作为网络加载完成前的默认数据)
            getDatas()
        }
    }

    /**
     * 获取数据
     */
    private fun getDatas() {
        // TODO: 10/21/2021  加入时间进行判断 - 如果5分钟之内, 则不重新请求加载
        loadHelper = LoadHelper(activity)
        loadHelper?.onPrepareListener = {
            Logma.v(TAG, "getDatas(): 开始获取模拟数据")// 开始
            wd_load_video.showVisible()
            if (wd_error_video != null) wd_error_video.showGone()
        }
        loadHelper?.onLoadSuccessListener = {
            Logma.i(TAG, "getDatas(): 获取成功")// 成功
            datas.addAll(it as ArrayList<ListBean>)
            listAdapter?.notifys(datas) // 刷新数据
            if (wd_load_video != null) wd_load_video.showGone()
            if (wd_error_video != null) wd_error_video.showGone()
        }
        loadHelper?.onLoadErrorListener = { error ->// 失败
            Logma.e(TAG, "getDatas(): 获取失败 = $error")
            if (wd_load_video != null) wd_load_video.showGone()
            wd_error_video.showVisible()
        }
        loadHelper?.loadVirtualContent(0)
    }

    /**
     * 初始化列表
     */
    private fun initAdapter() {
        Logma.v(TAG, "initAdapter(): 初始化适配器")
        rcv_video.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rcv_video.onScrollToBottomListener = { getDatas() }// 滑到底了 - 加载更多数据
        listAdapter = ListAdapter(activity, datas)
        rcv_video.adapter = listAdapter
    }

    /**
     * 初始化点击
     */
    private fun initListener() {
        // 双击了标题区域
        activity.wd_title.OnDoubleClickListener = {
            // TODO: 10/21/2021  加入防止频繁操作行为
            Logma.i(TAG, "initListener(): 双击了标题栏")
            getDatas()
        }
        // Title点击
        activity.wd_title.OnTitleClickListener = { enum ->
            Logma.i(TAG, "initListener(): 点击了 ${enum.name} 按钮")
            when (enum) {
                TitleWidget.TITLE_ENUM.SEARCH -> {
                    // TODO: 2021/10/13  点击了搜索按钮
                    lastFrag = Frag_video::class.java
                    toFrag(javaClass, Frag_search::class.java, null, true, 0)
                }
                TitleWidget.TITLE_ENUM.COLLECT -> {
                    // TODO: 2021/10/13  点击了收藏按钮
                    lastFrag = Frag_video::class.java
                    toFrag(javaClass, Frag_like::class.java, null, true, 0)
                }
            }
        }
        // 重试点击
        wd_error_video.onTryAaginListener = {
            Logma.i(TAG, "initListener(): 点击了重试")
            getDatas()
        }
    }

    override fun isReloadData(): Boolean {
        // TODO: 10/20/2021  此处加入日期判断, 如果小于5分钟则不刷新
        return super.isReloadData()
    }

    override fun onBackPresss(): Boolean {
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        activity.wd_tab.visibility = if (hidden) View.GONE else View.VISIBLE
        activity.wd_title.visibility = if (hidden) View.GONE else View.VISIBLE
        (activity as MainActivity).currentTag = if (hidden) "" else javaClass.simpleName
    }

}
