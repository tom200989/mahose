package com.mahose.mahose.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.logma.logma.tool.Logma
import com.mahose.mahose.BuildConfig

/*
 * Created by PD on 2021/10/9.
 */
class UpdateHelper {

    var test_flag = true // 测试跳转
    var onCheckStartListener: (() -> Unit)? = null // 开始检查
    var onCheckEndListener: ((need: Boolean) -> Unit)? = null // 检查结束(@param: 是否需要升级)

    /**
     * 检查升级
     */
    fun check() {
        // TODO: 2021/10/9  升级: 一天只需要检查1次
        onCheckStartListener?.invoke()
        onCheckEndListener?.invoke(false)
    }

    /**
     * 跳转GP
     */
    fun openUrl(activity: Activity) {
        val TAG = UpdateHelper::class.java.simpleName
        // 包名
        val currentPackageName = if (test_flag) "com.instagram.android" else BuildConfig.APPLICATION_ID
        val url = if (test_flag) "https://www.baidu.com" else "https://play.google.com/store/apps/details?id=$currentPackageName"
        try {
            // 手机有市场 - 跳转
            Logma.isd(TAG, "openGooglePlay() -> 准备打开谷歌市场")
            val currentPackageUri = Uri.parse("market://details?id=$currentPackageName")
            val intent = Intent(Intent.ACTION_VIEW, currentPackageUri)
            intent.setPackage("com.android.vending")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)

        } catch (e: Exception) {
            // 没有就打开浏览器
            Logma.esd(TAG, "openGooglePlay() -> 没有谷歌市场, 跳转到自带的浏览器")
            val currentPackageUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, currentPackageUri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        }
    }
}
