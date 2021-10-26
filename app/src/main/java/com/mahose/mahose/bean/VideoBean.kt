package com.mahose.mahose.bean

import android.graphics.Bitmap

/*
 * Created by 54484 on 10/26/2021.
 */
class VideoBean {
    var id: String? = null // ID
    var size: Long = 0L // 大小
    var video_width: Long = 0L // 宽
    var video_height: Long = 0L // 高
    var url: String? = null // 链接
    var duration: Long? = 0L // 时长
    var thumb: Bitmap? = null // 缩略图
    var thumb_width: Long = 0L // 缩略图宽
    var thumb_height: Long = 0L // 缩略图高
}
