package com.mahose.mahose.helper

import android.app.Activity
import com.mahose.mahose.R
import com.mahose.mahose.utils.OtherUtils

/*
 * Created by 54484 on 10/20/2021.
 */
class LoadHelper(activity: Activity) {

    var activity: Activity? = null
    var onLoadStartListener: (() -> Unit)? = null // 开始
    var onLoadSuccessListener: ((datas: Any) -> Unit)? = null // 成功
    var onLoadErrorListener: ((msg: String) -> Unit)? = null // 出错
    var onLoadProgressListener: ((progress: Int) -> Unit)? = null // 进度
    var onLoadFinishListener: (() -> Unit)? = null // 结束(无论成功与否)

    init {
        this.activity = activity
    }

    /**
     * 加载主内容数据(模拟数据:保留该方法进行测试)
     */
    fun loadVirtualContent(position:Int) {
        // TODO: 10/20/2021 模拟主内容数据 - 后期用网络请求取代
        Thread {
            try {
                activity?.runOnUiThread { onLoadStartListener?.invoke() }
                // 获取模拟数据 - todo 此处根据position去请求不同的链接
                val virturalDatas = OtherUtils.getVirturalContent(activity!!,position)
                activity?.runOnUiThread { if (virturalDatas.isNotEmpty()) onLoadSuccessListener?.invoke(virturalDatas) }
            } catch (e: Exception) {
                activity?.runOnUiThread { onLoadErrorListener?.invoke(e.message ?: activity!!.getString(R.string.unknown_error)) }
            } finally {
                activity?.runOnUiThread { onLoadFinishListener?.invoke() }
            }
        }.start()
    }

    /**
     * 加载副标题数据(模拟数据:保留该方法进行测试)
     */
    fun loadVirtualSubTitle() {
        // TODO: 10/20/2021 模拟副标题数据 - 后期用网络请求取代
        Thread {
            try {
                activity?.runOnUiThread { onLoadStartListener?.invoke() }
                // 获取模拟数据
                val virturalSubs = OtherUtils.getVirtualSubTitles()
                activity?.runOnUiThread { if (virturalSubs.isNotEmpty()) onLoadSuccessListener?.invoke(virturalSubs) }
            } catch (e: Exception) {
                activity?.runOnUiThread { onLoadErrorListener?.invoke(e.message ?: activity!!.getString(R.string.unknown_error)) }
            } finally {
                activity?.runOnUiThread { onLoadFinishListener?.invoke() }
            }
        }.start()
    }

}
