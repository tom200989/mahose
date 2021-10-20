package com.mahose.mahose.ue.frag

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.adapter.ListAdapter
import com.mahose.mahose.adapter.SubAdapter
import com.mahose.mahose.bean.ListBean
import com.mahose.mahose.bean.SubBean
import com.mahose.mahose.helper.LoadHelper
import com.mahose.mahose.ue.activity.MainActivity
import com.mahose.mahose.widget.TitleWidget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_pic.*

/*
 * Created by PD on 2021/10/9.
 */
class Frag_pic : RootFrag() {
    var TAG = javaClass.simpleName
    var listAdapter: ListAdapter? = null // 适配器(主内容区)
    var subAdapter: SubAdapter? = null // 适配器(副标题)
    var datas: ArrayList<ListBean> = ArrayList()// 数据(主内容区)
    var subTitles: ArrayList<SubBean> = ArrayList()// 数据(副标题)
    var loadHelper: LoadHelper? = null

    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        activity.wd_title.visibility = View.VISIBLE
        (activity as MainActivity).currentTag = javaClass.simpleName
        return R.layout.frag_pic
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化点击
        initListener()
        // 初始化列表
        initAdapter()
        // 初始化加载数据 (要放在所有的控件初始化最后 - 未来可以作为网络加载完成前的默认数据)
        getDatas()
    }

    /**
     * 获取数据
     */
    private fun getDatas() {
        // 获取副标题
        getSubTitle()
        // 获取主内容
        getContent(0)
    }

    /**
     * 获取副标题
     */
    private fun getSubTitle() {
        loadHelper = LoadHelper(activity)
        loadHelper?.onLoadSuccessListener = {
            rcv_pic_subTitle.visibility = View.VISIBLE
            subTitles = it as ArrayList<SubBean>
            subAdapter?.notifys(subTitles)
        }
        loadHelper?.onLoadErrorListener = {
            rcv_pic_subTitle.visibility = View.GONE
        }
        loadHelper?.loadVirtualSubTitle()
    }

    /**
     * 获取主内容
     */
    private fun getContent(position: Int) {
        loadHelper = LoadHelper(activity)
        loadHelper?.onLoadStartListener = {
            Logma.v(TAG, "getDatas(): 开始获取模拟数据")// 开始
            wd_load_pic.showVisible()
            wd_error_pic.showGone()
        }
        loadHelper?.onLoadSuccessListener = {
            Logma.i(TAG, "getDatas(): 获取成功")// 成功
            datas = it as ArrayList<ListBean>
            listAdapter?.notifys(datas) // 刷新数据
            wd_load_pic.showGone()
            wd_error_pic.showGone()
        }
        loadHelper?.onLoadErrorListener = { error ->// 失败
            Logma.e(TAG, "getDatas(): 获取失败 = $error")
            wd_load_pic.showGone()
            wd_error_pic.showVisible()
        }
        loadHelper?.loadVirtualContent(position)
    }

    /**
     * 初始化列表
     */
    private fun initAdapter() {
        Logma.v(TAG, "initAdapter(): 初始化适配器")
        // 副标题
        rcv_pic_subTitle.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        subAdapter = SubAdapter(activity, subTitles)
        subAdapter?.onItemClickListener = { position, subTitle -> clickItem(position, subTitle) } // 点击item
        rcv_pic_subTitle.adapter = subAdapter

        // 主内容
        rcv_pic.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        listAdapter = ListAdapter(activity, datas)
        rcv_pic.adapter = listAdapter
    }

    /**
     * 初始化点击
     */
    private fun initListener() {
        // 双击了标题区域
        activity.wd_title.OnDoubleClickListener = {
            Logma.i(TAG, "initListener(): 双击了标题栏")
            getDatas()
        }
        // Title点击
        activity.wd_title.OnTitleClickListener = { enum ->
            Logma.i(TAG, "initListener(): 点击了 ${enum.name} 按钮")
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
        wd_error_pic.onTryAaginListener = {
            Logma.i(TAG, "initListener(): 点击了重试")
            getDatas()
        }
    }

    /**
     * 点击Item的操作
     */
    private fun clickItem(position: Int, subTitle: String) {
        // 点击Item操作 - 根据类型加载不同的数据
        Logma.i(TAG, "clickItem(): 点击了 $subTitle 副标题")
        getContent(position)
    }

    override fun isReloadData(): Boolean {
        // TODO: 10/20/2021  此处加入日期判断, 如果小于1个小时则不刷新
        return super.isReloadData()
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
