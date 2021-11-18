package com.mahose.mahose.bean

import android.graphics.Bitmap
import com.mahose.mahose.bean.LikeBean.LIKE_TYPE.*

/*
 * Created by 54484 on 11/16/2021.
 */
class LikeBean {
    var id: String? = null // ID
    var type: LIKE_TYPE? = UNKNOWN // 类型
    var thumb: Bitmap? = null // 缩略图
    var url: String? = null // 详情链接

    enum class LIKE_TYPE {
        PIC, VIDEO, UNKNOWN
    }
}
