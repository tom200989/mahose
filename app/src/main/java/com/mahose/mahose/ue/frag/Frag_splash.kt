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
                Logma.vsd(TAG, "toCheckVersion() -> 进入升级流程")
                toUpgrade()

            } else {// 不需要升级的 - 直接跳转到主页
                Logma.vsd(TAG, "toCheckVersion() -> 跳转到主页")
                toFrag(javaClass, Frag_home::class.java, null, true, 2000)
            }
        }
        updateHelper.check()
    }

    /**
     * 进入升级流程
     */
    private fun toUpgrade() {
        Logma.vsd(TAG, "toCheckVersion() -> 进入正式升级流程")
        val updateHelper = UpdateHelper()
        // 升级开始
        updateHelper.onUpdateStartListener = {
            Logma.vsd(TAG, "toUpgrade() -> 即将开始升级")
            ll_upgrade.visibility = View.VISIBLE
        }
        // 升级进度
        updateHelper.onUpdateProgressListener = { tv_upgrade_progress.text = "$it%" }
        // 升级完成
        updateHelper.onUpdateFinishListener = {
            Logma.isd(TAG, "toUpgrade() -> 升级完成")
            tv_upgrade_des.text = getRootString(R.string.version_will_be_done)
            tv_upgrade_progress.text = "100%"
        }
        // 升级错误
        updateHelper.onUpdateErrorListener = {
            Logma.esd(TAG, "toUpgrade() -> 升级错误: $it")
            tv_upgrade_des.text = getRootString(R.string.current_upgrade_error)
        }
        updateHelper.update(activity)
    }
    
}
