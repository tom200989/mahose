package com.mahose.mahose.helper

import android.app.Activity
import com.mahose.mahose.R
import com.mahose.mahose.utils.OtherUtils
import com.p_runtext.p_runtext.utils.Other

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
     * 加载数据(模拟数据:保留该方法进行测试)
     */
    fun loadVirtual() {
        // TODO: 10/20/2021 模拟数据 - 后期用网络请求取代
        Thread {
            try {
                activity?.runOnUiThread { onLoadStartListener?.invoke() }
                // 获取模拟数据
                val virturalDatas = OtherUtils.getVirturalDatas(activity!!)
                activity?.runOnUiThread { if (virturalDatas.isNotEmpty()) onLoadSuccessListener?.invoke(virturalDatas) }
            } catch (e: Exception) {
                activity?.runOnUiThread { onLoadErrorListener?.invoke(e.message ?: activity!!.getString(R.string.unknown_error)) }
            } finally {
                activity?.runOnUiThread { onLoadFinishListener?.invoke() }
            }
        }.start()
    }

}
