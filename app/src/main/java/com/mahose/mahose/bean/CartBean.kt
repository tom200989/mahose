package com.mahose.mahose.bean

import android.graphics.Bitmap

/*
 * Created by 54484 on 11/11/2021.
 */
class CartBean {
    var type: TYPE = TYPE.NORMAL // 普通类型/总价类型
    var pic: Bitmap? = null // 缩略图
    var title: String? = null // 标题
    var subtitle: String? = null // 副标题(商品ID或其他)
    var count: Int = 1 // 数量
    var price: Long = 0 // 单价


    enum class TYPE {
        NORMAL, SUBTOTAL
    }
}
