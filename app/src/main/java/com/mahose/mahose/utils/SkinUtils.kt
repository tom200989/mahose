package com.ktapp.skin

import android.app.Application
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class SkinUtils {

    companion object {

        private var app: Application? = null

        /* 1.支持的换肤属性(根据业务需求扩展) TODO: 2021/6/7  未来此处可拓展 */
        private val mAttrs: List<String> = listOf("background", "src", "textColor")

        /* 2.View:hashCode与属性体的映射 */
        val skinMap: HashMap<View, List<LayBean>> = HashMap()

        /* 3.包名 */
        var pkgName: String? = null

        /* 4.新皮肤资源 */
        var newSkinResource: Resources? = null

        /**
         * 5.对View设置属性 (SkinFactory每解析完一个View就调用一次该方法)
         *
         * attrs含有信息,如:
         * android:textColor = @color/colorAccent
         * android:src = @drawable/ic_launch
         */
        fun setAttrs(app: Application?, view: View, attrs: AttributeSet) {
            this.app = app
            val attrList: ArrayList<LayBean> = ArrayList()
            // 遍历属性
            for (i in 0 until attrs.attributeCount) {
                // 属性名 layName = (android:textColor)
                val layName = attrs.getAttributeName(i)
                // 属性值
                if (mAttrs.contains(layName)) {
                    // attrs.getAttributeValue(i)  = "@2130968614" (得到的是字符 -> 需经下方转换为int)
                    if (attrs.getAttributeValue(i).startsWith("#")) {
                        continue // 配置文件写死的值无法换肤
                    }
                    if (attrs.getAttributeValue(i).startsWith("@")) {
                        // @2130968614 (把字符转为int型)
                        val layValue = attrs.getAttributeValue(i).substring(1).toInt()
                        // 添加到集合进行记录
                        attrList.add(LayBean(layName, layValue))
                    }

                }
            }

            // 如果该View有指定的属性
            if (attrList.isNotEmpty()) {
                skinMap[view] = attrList
                // 则加载皮肤
                applySkin(view, skinMap)
            }
        }

        /**
         * 6.加载皮肤
         * @param view 需要操作的view
         * @param skinMap 该view对应的属性体
         */
        fun applySkin(view: View, skinMap: HashMap<View, List<LayBean>>) {
            putVerb("------------------------------------------------------")
            // 找到对应View的属性体
            val attrsBeans = skinMap[view]
            // 遍历
            for (attrsBean in attrsBeans!!) {
                val layName = attrsBean.layName // layName: textColor
                val layValue = attrsBean.layValue // layValue: 236698226(<color name = "co_name"> #000 </color>)

                // 根据当前值在新皮肤资源包得到对应的值
                val newLayoutValue = getNewLayValue(newSkinResource, layValue)
                
                // 指定标签类型判断
                if (layName == "textColor") {
                    val color = newLayoutValue?.let { newSkinResource?.getColor(it) }
                    color?.let { (view as TextView).setTextColor(it) }
                }

                if (layName == "background") {
                    val drawable = newSkinResource?.getDrawable(newLayoutValue!!)
                    // 如果XML设置的是颜色值
                    if (drawable is ColorDrawable) {
                        // 注意这里不能直接把drawable强转给setBackgroudColor当参数 - 否则报错; 要以颜色的方式getColor得到颜色对象
                        val color = newSkinResource?.getColor(newLayoutValue!!)
                        view.setBackgroundColor(color!!)
                    }
                    // 如果XML设置的是图片资源
                    if (drawable is BitmapDrawable) view.background = drawable
                }

                if (layName == "src") {
                    val drawable = newSkinResource?.getDrawable(newLayoutValue!!)
                    // 如果XML设置的是颜色值
                    if (drawable is ColorDrawable) {
                        // 注意这里不能直接把drawable强转给setBackgroudColor当参数 - 否则报错; 要以颜色的方式getColor得到颜色对象
                        val iv = view as ImageView
                        iv.setImageDrawable(ColorDrawable(newSkinResource?.getColor(newLayoutValue!!)!!))
                    }
                    // 如果XML设置的是图片资源
                    if (drawable is BitmapDrawable) {
                        val iv = view as ImageView
                        iv.setImageDrawable(drawable)
                    }
                }

                // TODO: 2021/6/7  未来此处可拓展
            }
        }

        /**
         * 替换属性值
         * @param newResource 新皮肤资源
         * @param layValue 原皮肤值
         */
        fun getNewLayValue(newResource: Resources?, layValue: Int): Int? {
            
            /*  
            * 原理: 一个layValue等同于一条资源信息, 如<color name = "colorAccent"> #000123 </color>
            * 从宿主中的layValue (如@2368999665) 中
            * 获取到对应资源标签的类型, 如<color>
            * 获取到对应资源标签的名字, 如"colorAccent"
            * 
            * 通过<类型><名字><包名>在皮肤资源中即可找到对应的新值
            * 
            * */

            // 取得老资源里的 - 标签节点类型
            // <color name="colorAccent"> #ffffff </color>                -> <color>
            val defType = app?.resources?.getResourceTypeName(layValue)

            // 取得老资源里的 - 取得节点属性名
            // <color name="colorAccent"> #ffffff </color>                -> "colorAccent"
            val name = app?.resources?.getResourceEntryName(layValue)

            // 根据<类型>和<名称>获得 - 新皮肤的对应值ID -- @263963578
            return newResource?.getIdentifier(name, defType, pkgName)
        }

    }
}

/**
 * 3.属性体
 * <textcolor, @color/colorAccent>
 * <backgroud, @drawable/bg>
 */
data class LayBean(val layName: String, val layValue: Int)

fun putVerb(content: String) {
    Log.v("Skin-ma", content)
}
