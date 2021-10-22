package com.ktapp.skin

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Environment
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.io.File
import java.lang.reflect.Constructor
import java.util.*

/* 1.创建换肤工厂 - 目的是hook住SDK里的Factory2接口 */
open class SkinFactory : LayoutInflater.Factory2 {

    private var app: Application? = null

    // private var skinPath: String = "/data/data/com.ktapp.ktapp/skin/skin-debug.apk" // 皮肤包地址(固定)
    private var skinPath: String? = null

    /**
     * @param path皮肤APK路径
     */
    constructor(app: Application, path: String) {
        this.app = app
        skinPath = path
        // 非空判断
        if (TextUtils.isEmpty(skinPath)) return
        if (!File(skinPath!!).exists()) return
        // 重置: 每一个Activity/Fragment初始化时都把上一个view的属性记录重置
        SkinUtils.skinMap.clear()
        // 得到新皮肤包包名
        SkinUtils.pkgName = getPkgName()
        // 获取到新皮肤资源
        SkinUtils.newSkinResource = getNewSkinResource()
    }

    /**
     * 得到新皮肤包资源
     * @return 新皮肤资源
     */
    private fun getNewSkinResource(): Resources {
        // 宿主app的Resource (目的是为了拿到「displayMetrics」「configuration」;
        val appResource: Resources? = app?.resources
        // 反射AssetManager
        val assetManager = AssetManager::class.java.newInstance()
        // 反射AssetManager:addAssetPath()方法
        val method = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
        // 传递新皮肤的路径
        method.invoke(assetManager, skinPath)
        // 把新的AssetManager重新传递进去 (displayMetrics和configuration采用宿主配置)
        return Resources(assetManager, appResource?.displayMetrics, appResource?.configuration)
    }

    /**
     * 得到新皮肤包包名
     * @return 包名
     */
    private fun getPkgName(): String? {
        val pm: PackageManager? = app?.packageManager
        val info = pm?.getPackageArchiveInfo(skinPath!!, PackageManager.GET_ACTIVITIES)
        return info?.packageName
    }

    /* 
    * 2.几类常用的视图包包名
    * (目的用于后面的全限包名拼接)
    */
    val mClassPrefixList = arrayOf("android.widget.", "android.webkit.", "android.app.", "android.view.")

    /* 
     * 3.View构造方法所需的参数(用于通过构造方法创建view)
     * public xxx(Context context, AttributeSet attrs) 
     */
    private val mConstructorSignature: Array<Class<*>> = arrayOf(Context::class.java, AttributeSet::class.java)

    /* 
    * 4.记录对应view 名字与构造的对应关系
    * <LinearLayout, LinearLayout(context, attr)> 
    */
    private val mConstructorMap: HashMap<String, Constructor<out View?>> = HashMap()

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        /* 5.SDK每解析一个xml的标签都会回调一个View */
        return toCreatedView(name, context, attrs)
    }

    /* 5.根据SDK返回的参数进行view创建 */
    private fun toCreatedView(name: String, context: Context, attrs: AttributeSet): View? {

        // @name: 控件名称, 如TextView,ImageView
        // @attrs: 该控件资源属性, 如<color><drawable>等

        // 自定义view (com.xx.yy.zz这种形式的标签) - 不操作
        if (name.contains(".")) return null
        // 根据SDK包名不同进行创建
        for (prefix in mClassPrefixList) {
            try {// 拼接全名
                val fullname = prefix + name
                // 查询是否加载过
                var constructor: Constructor<out View?>? = mConstructorMap[fullname]
                // 如果没有加载过
                if (constructor == null) {
                    // 字节码加载器加载出View的构造方法体
                    val classLoader = context.classLoader
                    val asSubclass = classLoader.loadClass(fullname).asSubclass(View::class.java)
                    constructor = asSubclass.getConstructor(*mConstructorSignature)
                }
                // 通过构造创建出View
                val view: View? = constructor?.newInstance(context, attrs)
                if (view != null) {
                    mConstructorMap[fullname] = constructor!!
                    /* (核心代码) 每解析完一个xml标签 - 就设置一次View属性 */
                    SkinUtils.setAttrs(app, view, attrs)
                    return view
                }
            } catch (e: Exception) {
                // 加异常块的目的是为了适配所有的布局和控件的不同包名
                // 每一个View都尝试适用SDK常用的四种包名进行匹配, 取成功的那一个
                continue
            }
        }
        return null
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }
}
