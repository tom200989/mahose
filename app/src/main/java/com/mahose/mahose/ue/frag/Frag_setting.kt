package com.mahose.mahose.ue.frag

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import com.hiber.hiber.RootFrag
import com.logma.logma.tool.Logma
import com.mahose.mahose.BuildConfig
import com.mahose.mahose.R
import com.mahose.mahose.helper.LoginHelper
import com.mahose.mahose.utils.OtherUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_setting.*
import java.util.*
import kotlin.math.abs
import kotlin.reflect.KClass

/*
 * Created by PD on 2021/10/9.
 */
class Frag_setting : RootFrag() {

    val TAG: String = javaClass.simpleName
    var current_open = false // 当前左侧面板是否开启
    var isPoping = false // 左侧面板是否正在开启
    var loginHelper: LoginHelper? = null
    var enum: LoginHelper.LOGIN_ENUM? = null // 登录状态(登录\登出\未注册)

    override fun onInflateLayout(): Int {
        // 显示tab
        activity.wd_tab.visibility = View.VISIBLE
        return R.layout.frag_setting
    }

    override fun onNexts(p0: Any?, p1: View?, p2: String?) {
        // 初始化
        initData()
        // 点击事件
        initListener()

    }

    /**
     * 初始化数据
     */
    private fun initData() {
        // 获取版本号
        tv_setting_version.text = BuildConfig.VERSION_NAME
        // 检查登录\登出\注册状态
        getLoginState()
    }

    /**
     * 检查登录\登出\注册状态
     */
    private fun getLoginState() {
        loginHelper = LoginHelper()
        loginHelper?.onPrepareListener = { wd_load_setting.showVisible() }
        loginHelper?.onStateListener = { enum ->
            this.enum = enum // 状态
            when (enum) {
                LoginHelper.LOGIN_ENUM.LOGIN -> {
                    // TODO: 10/27/2021 获取头像 + 用户名
                    tv_setting_login.text = getString(R.string.to_logout)
                }
                LoginHelper.LOGIN_ENUM.LOGOUT -> {
                    // TODO: 10/27/2021  显示默认头像
                    tv_setting_login.text = getString(R.string.to_login)
                }
                LoginHelper.LOGIN_ENUM.UNREGISTER -> {
                    // TODO: 10/27/2021  显示默认头像
                    tv_setting_login.text = getString(R.string.to_registe)
                }
            }
        }
        loginHelper?.onEndListener = { wd_load_setting.showGone() }
        loginHelper?.getState()
    }

    /**
     * 初始化点击
     */
    private fun initListener() {
        // 点击更多
        rl_setting_more.setOnClickListener { popMenu() }
        // 蒙版点击
        iv_setting_mask.setOnClickListener { popMenu() }
        // 切换主题
        rl_setting_theme.setOnClickListener {
            toFrag(javaClass, Frag_theme::class.java, null, true, 0)
        }
        // 切换语言
        rl_setting_language.setOnClickListener {
            toFrag(javaClass, Frag_language::class.java, null, true, 0)
        }
        // 隐私条款
        tv_setting_privacy.setOnClickListener {
            toFrag(javaClass, Frag_privacy::class.java, null, true, 0)
        }
        // 前往购物车
        rl_setting_cart.setOnClickListener { toPage(Frag_cart::class.java) }
        // 前往客服
        rl_setting_chat.setOnClickListener { toPage(Frag_chat::class.java) }
        // 登出弹窗OK
        wd_logout.onLogoutClickOkListener = {
            // TODO: 11/2/2021  发起登出请求
        }
        // 登录登出注册
        rl_setting_login.setOnClickListener {
            // TODO: 10/22/2021  登录登出
            when (enum) {
                LoginHelper.LOGIN_ENUM.LOGIN -> wd_logout.visibility = View.VISIBLE
                LoginHelper.LOGIN_ENUM.LOGOUT -> {
                    // TODO: 11/2/2021  跳转到登录页面
                }
                LoginHelper.LOGIN_ENUM.UNREGISTER -> {
                    // TODO: 11/2/2021  跳转到注册页面
                }
            }
        }
    }

    /**
     * 跳转页面
     */
    private fun toPage(clazz: Class<out RootFrag>) {
        if (enum == LoginHelper.LOGIN_ENUM.LOGIN) {
            toFrag(javaClass, clazz, null, true, 0)
        } else {
            toast(getString(R.string.please_login_first), 3000)
        }
    }

    /**
     * 弹出左侧面板
     */
    private fun popMenu() {

        Thread {
            val offset = rl_setting_left.width.toFloat() // 左侧区宽
            var tick = 0.0f // 左侧区计步初始值
            val step = 6 // 左侧区步进

            val al_max = 0.7f // 蒙版透明度最大值
            var al_tick = 0.0f // 蒙版计步初始值
            val al_del = 0.01f // 蒙版偏差
            val al_step = al_max / (offset / step) // 蒙版步进

            while (!isPoping) {

                Thread.sleep(1)
                tick += step // 主区和侧区变化值
                al_tick += al_step // 递进蒙版透明度变化值

                activity.runOnUiThread {
                    // ----- 移动: 左侧、主区
                    OtherUtils.move_view_x(rl_setting_left, if (!current_open) tick else offset - tick)
                    OtherUtils.move_view_x(rl_setting_content, if (!current_open) tick else offset - tick)

                    // ----- 蒙版透明度 + 大小 
                    if (al_tick >= al_max - al_del) al_tick = al_max
                    // 透明度
                    if (!current_open) iv_setting_mask.visibility = View.VISIBLE
                    iv_setting_mask.alpha = if (!current_open) al_tick else if (al_max - al_tick <= al_del) 0.0f else al_max - al_tick
                    if (iv_setting_mask.alpha == 0.0f) iv_setting_mask.visibility = View.GONE
                    // 蒙版偏距
                    val mask_layoutParam = iv_setting_mask.layoutParams as FrameLayout.LayoutParams
                    mask_layoutParam.marginStart = if (!current_open) tick.toInt() else (offset - tick).toInt()
                    iv_setting_mask.layoutParams = mask_layoutParam
                }

                // 跳出循环
                if (tick >= abs(offset)) isPoping = true
            }

            // 此处添加延迟100ms的原因是因为while循环结束前 - 上一次的runOnUi可能还在执行
            // 如果此时马上current_open置反 - 就会导致蒙版闪烁
            Handler(Looper.getMainLooper()).postDelayed({
                current_open = !current_open
                isPoping = false // 復位       
            }, 100)

        }.start()
    }

    override fun onBackPresss(): Boolean {
        // 侧边板如果打开 - 则处理侧边板逻辑
        if (current_open) {
            popMenu()
            return true
        }
        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) activity.wd_tab.visibility = View.GONE
    }
}
