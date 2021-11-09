package com.mahose.mahose.ue.frag

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiber.hiber.RootFrag
import com.hiber.hiber.language.RootApp
import com.mahose.mahose.R
import com.mahose.mahose.adapter.ChatAdapter
import com.mahose.mahose.bean.ChatBean
import com.mahose.mahose.utils.OtherUtils
import kotlinx.android.synthetic.main.frag_chat.*

/*
 * Created by 54484 on 10/27/2021.
 */
class Frag_chat : RootFrag() {

    var chatbeans: ArrayList<ChatBean> = ArrayList()
    var adapter: ChatAdapter? = null

    override fun onInflateLayout(): Int {
        return R.layout.frag_chat
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化数据
        initData()
        // 初始化适配
        initAdapter()
        // 初始化事件
        initClick()
    }

    /**
     * 初始化事件
     */
    private fun initClick() {
        // 回退
        rl_chat_back.setOnClickListener { onBackPresss() }
        // 选择图片
        rl_chat_add_pic.setOnClickListener{selectPic()}
        // 发送消息
        bt_chat_send.setOnClickListener { sendMessage() }
    }

    /**
     * 发送文本消息
     */
    private fun sendMessage() {
        val content = et_chat.text.toString()
        if (content.isEmpty()) return
        // TODO: 11/9/2021  发送文本消息
    }

    /**
     * 选择图片
     */
    private fun selectPic() {
        // TODO: 11/9/2021  选择图片
    }

    /**
     * 初始化适配
     */
    private fun initAdapter() {
        val lm = LinearLayoutManager(activity)
        adapter = ChatAdapter(activity, chatbeans)
        adapter?.onPicClickListener = this::openPicUrl
        rcv_chat.adapter = adapter
        rcv_chat.layoutManager = lm
    }

    /**
     * 打开图片链接大图
     */
    private fun openPicUrl(picUrl: String) {
        // TODO: 11/9/2021 打开图片URL链接
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // 模拟数据
        chatbeans = OtherUtils.getChatInfo(activity)
    }

    override fun onBackPresss(): Boolean {
        // 回退设置页
        toFrag(javaClass, Frag_setting::class.java, null, true, 0)
        return true
    }
}
