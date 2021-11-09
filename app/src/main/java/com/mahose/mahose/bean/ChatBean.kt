package com.mahose.mahose.bean

import android.graphics.Bitmap

/*
 * Created by 54484 on 11/8/2021.
 */
class ChatBean {
    var isKefu: Boolean = true // 是否为客服
    var type: CHAT_TYPE? = null // 类型
    var head_bitmap: Bitmap? = null // 头像
    var content: String? = null // 内容
    var pic: Bitmap? = null // 截图
    var pic_url:String?=null // 截图大图地址
    var time: String? = null // 时间

    enum class CHAT_TYPE {
        TEXT, PIC
    }
}
