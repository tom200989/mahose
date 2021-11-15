package com.mahose.mahose.bean

import android.graphics.Bitmap

/*
 * Created by 54484 on 11/11/2021.
 */
class CartBean {
    var pic: Bitmap? = null // 缩略图
    var title: String? = null // 标题
    var subtitle: String? = null // 副标题(商品ID或其他)
    var count: Int = 1 // 数量
    var price: Long = 0 // 单价
}
