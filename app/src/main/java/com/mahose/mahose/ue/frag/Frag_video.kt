package com.mahose.mahose.ue.frag

import android.view.View
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
    var adapter: ListAdapter? = null
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
        // 初始化点击
        initListener()
        // 初始化列表
        initAdapter()
        // 加载数据 (要放在所有的控件初始化最后 - 未来可以作为网络加载完成前的默认数据)
        getDatas()
    }

    /**
     * 获取数据
     */
    private fun getDatas() {
        loadHelper = LoadHelper(activity)
        loadHelper?.onLoadStartListener = {
            Logma.v(TAG, "getVirtualDatas(): 开始获取模拟数据")// 开始
            wd_load_video.showVisible()
            wd_error_video.showGone()
        }
        loadHelper?.onLoadSuccessListener = {
            Logma.i(TAG, "getVirtualDatas(): 获取成功")// 成功
            adapter?.notifys(it as ArrayList<ListBean>)
            wd_load_video.showGone()
            wd_error_video.showGone()
        }
        loadHelper?.onLoadErrorListener = { error ->// 失败
            Logma.e(TAG, "getVirtualDatas(): 获取失败 = $error")
            wd_load_video.showGone()
            wd_error_video.showVisible()
        }
        loadHelper?.loadVirtual()
    }

    /**
     * 初始化列表
     */
    private fun initAdapter() {
        Logma.v(TAG, "initAdapter(): 初始化适配器")
        rcv_video.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = ListAdapter(activity, datas)
        rcv_video.adapter = adapter
    }

    /**
     * 初始化点击
     */
    private fun initListener() {
        // 双击了标题区域
        activity.wd_title.OnDoubleClickListener = {
            Logma.i(TAG, "双击了标题栏")
            getDatas()
        }
        // Title点击
        activity.wd_title.OnTitleClickListener = { enum ->
            Logma.i(TAG, "点击了 ${enum.name} 按钮")
            when (enum) {
                TitleWidget.TITLE_ENUM.SEARCH -> {
                    // TODO: 2021/10/13  点击了搜索按钮
                }
                TitleWidget.TITLE_ENUM.COLLECT -> {
                    // TODO: 2021/10/13  点击了收藏按钮
                }
            }
        }
        // 重试点击
        wd_error_video.onTryAaginListener = {
            Logma.i(TAG, "点击了重试")
            getDatas()
        }
    }

    override fun onBackPresss(): Boolean {
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) activity.wd_tab.visibility = View.GONE
        if (hidden) activity.wd_title.visibility = View.GONE
        if (hidden) (activity as MainActivity).currentTag = ""
    }

}
