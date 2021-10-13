package com.mahose.mahose.ue.frag

import android.Manifest
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.view.View
import com.hiber.bean.PermissBean
import com.hiber.bean.StringBean
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.R
import com.mahose.mahose.helper.UpdateHelper
import com.mahose.mahose.widget.TabWidget
import com.mahose.mahose.widget.TitleWidget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_splash.*


/*
 * Created by PD on 2021/10/9.
 */
@SuppressLint("SetTextI18n")
class Frag_splash : RootFrag() {

    var TAG = javaClass.simpleName

    /**
     * 申请权限
     */
    override fun initPermissed(): Array<String> {

        // 权限监听
        setPermissedListener { isAllPass, notPassPermiss ->
            Logma.v(TAG, "initPermissed() ->权限是否全部通过? $isAllPass")
            notPassPermiss.forEach { Logma.v(TAG, "initPermissed() -> 未通过的权限 = $it") }
            if (!isAllPass) onBackPresss() // 如果权限未通过 - 退出APP
        }

        // 开始申请
        val permissBean = PermissBean()
        val stringBean = StringBean()
        stringBean.cancel = getRootString(R.string.cancel)
        stringBean.ok = getRootString(R.string.ok)
        stringBean.content = getRootString(R.string.if_you_want_to_use_app)
        stringBean.title = getRootString(R.string.permission_application)
        permissBean.stringBean = stringBean
        setPermissBean(permissBean)
        val externalStorage: String = Manifest.permission.READ_EXTERNAL_STORAGE
        Logma.vsd(TAG, "initPermissed() -> 申请权限[READ_EXTERNAL_STORAGE]")
        return arrayOf(externalStorage)
    }

    /**
     * 加载视图
     */
    override fun onInflateLayout(): Int {
        // 日志初始化
        Logma.dir_name = "mahose"
        Logma.init(activity)
        return R.layout.frag_splash
    }

    /**
     * 页面加载完毕
     */
    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 检查更新 (第一个页面中有跳转, 需要用handler包裹, 否则视图可能未加载完毕出现白屏)
        Handler(Looper.getMainLooper()).postDelayed({ toCheckVersion() }, 200)
        // 设置tab点击
        setListener()
    }

    /**
     * 设置点击事件
     */
    private fun setListener() {
        /* TAB点击 */
        activity.wd_tab.onTabClickListener = { enum ->
            Logma.i(TAG, "正在切换 ${enum.name} 页")
            when (enum) {
                // 跳转到视频页
                TabWidget.TAB_ENUM.VIDEO -> {
                    toFrag(javaClass, Frag_video::class.java, null, true, 0)
                    activity.wd_title.setTitle(getRootString(R.string.video))
                }
                // 跳转到图片页
                TabWidget.TAB_ENUM.PIC -> {
                    toFrag(javaClass, Frag_pic::class.java, null, true, 0)
                    activity.wd_title.setTitle(getRootString(R.string.gallary))
                }

                // 跳转到设置页
                TabWidget.TAB_ENUM.SETTING -> toFrag(javaClass, Frag_setting::class.java, null, true, 0)
            }
        }

        /* Title点击 */
        activity.wd_title.OnTitleClickListener = { enum ->
            Logma.i(TAG, "点击了 ${enum.name} 按钮")
            when (enum) {
                TitleWidget.TITLE_ENUM.SEARCH -> {
                    // TODO: 2021/10/13  点击了搜索按钮
                }
                TitleWidget.TITLE_ENUM.COLLECT -> {
                    // TODO: 2021/10/13  点击了收藏按钮
                }
            }
        }

        /* 升级弹窗 */
        wd_upgrade_tip.onTipClickOkListener = {
            Logma.vsd(TAG, "onTipClickOkListener() -> 打开google play app页面, 让用户自己去下载安装")
            UpdateHelper().openUrl(activity)
        }
    }

    /**
     * 点击返回键
     */
    override fun onBackPresss(): Boolean {
        Logma.vsd(TAG, "onBackPresss() -> 退出")
        killAllActivitys()
        Process.killProcess(Process.myPid())
        return true
    }

    /* -------------------------------------------- private -------------------------------------------- */

    /**
     * 检查更新
     */
    private fun toCheckVersion() {
        Logma.vsd(TAG, "toCheckVersion() -> 进入检查升级流程")
        val updateHelper = UpdateHelper()
        updateHelper.onCheckStartListener = { Logma.vsd(TAG, "checkUpgrade() -> 开始检查升级") }
        updateHelper.onCheckEndListener = { isneed ->
            // 如需升级 - 弹出视图直接联网升级
            if (isneed) {
                Logma.vsd(TAG, "toCheckVersion() -> 显示升级弹窗")
                wd_upgrade_tip.visibility = View.VISIBLE

            } else {// 不需要升级的 - 直接跳转到主页
                Logma.vsd(TAG, "toCheckVersion() -> 跳转到主页")
                toFrag(javaClass, Frag_video::class.java, null, true, 1000)
            }
        }
        updateHelper.check()
    }

}
