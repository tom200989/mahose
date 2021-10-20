package com.mahose.mahose.bean

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/*
 * 视频对象
 */
class ListBean() {
    var thumb_width: Float = 0f // 缩略主图宽度
    var thumb_height: Float = 0f // 缩略主图高度
    var head_bitmap: Bitmap? = null // 头像图片
    var main_bitmap: Bitmap? = null // 主体图片
    var title: String? = null // 标题
    var isLike: Boolean = false // 是否为喜欢
    var likeNum: Long = 0 // 喜欢数量
}
