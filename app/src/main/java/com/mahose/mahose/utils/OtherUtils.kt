package com.mahose.mahose.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Build
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.mahose.mahose.R
import com.mahose.mahose.bean.Cons
import com.mahose.mahose.bean.ListBean
import com.mahose.mahose.bean.SubBean
import com.mahose.mahose.bean.ThemeBean
import com.nineoldandroids.view.ViewHelper
import com.rootmastatic.rootmastatic.util.SPUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import kotlin.random.Random


/*
 * Created by 54484 on 10/19/2021.
 */
class OtherUtils {

    companion object {
        // 副标题资源
        val titles = arrayOf("Cloths", "Scene", "Animal", "Food", "Drink", "Fruit", "Archicle")

        // 图片资源
        val pics = arrayListOf(
            arrayOf(R.drawable.test0, R.drawable.test1), // 服饰
            arrayOf(R.drawable.test2, R.drawable.test3), // 风景
            arrayOf(R.drawable.test4, R.drawable.test5), // 宠物
            arrayOf(R.drawable.test6, R.drawable.test7), // 美食
            arrayOf(R.drawable.test8, R.drawable.test9), // 饮料
            arrayOf(R.drawable.test10, R.drawable.test11), // 水果
            arrayOf(R.drawable.test12, R.drawable.test13) // 建筑
        )

        /**
         * 获取主内容模拟数据
         */
        fun getVirturalContent(activity: Activity, position: Int): ArrayList<ListBean> {
            val pic_arr = pics[position]
            val datas = ArrayList<ListBean>()
            for (idx in 0..20) {// TODO: 10/20/2021 当数据较大时, 出现线程挂起的现象(用多线程解决)
                val listBean = ListBean()
                // 随机
                val mod = Random.nextInt(0, 50) % 2
                if (mod == 0) {
                    // 主图
                    val bitmap = get_scale_bitmap_by_drawable(activity, pic_arr[0])
                    listBean.thumb_width = bitmap.width.toFloat() // 宽高
                    listBean.thumb_height = bitmap.height.toFloat() // 宽高
                    listBean.main_bitmap = bitmap // 图元
                    listBean.title = "这是一个短标题" // 标题
                    listBean.isLike = true // 小心心

                } else {
                    // 主图
                    val bitmap = get_scale_bitmap_by_drawable(activity, pic_arr[1])
                    listBean.thumb_width = bitmap.width.toFloat() // 宽高
                    listBean.thumb_height = bitmap.height.toFloat() // 宽高
                    listBean.main_bitmap = bitmap // 图元
                    listBean.title = "这是一个很长很长很长很长很长很长非常长的标题" // 标题
                    listBean.isLike = false // 小心心
                }

                listBean.head_bitmap = draw_to_bitmap(activity, R.drawable.head_default)// 头像
                listBean.likeNum = Random.nextInt(0, 20000).toLong() // 喜欢人数

                datas.add(listBean) // 加入到集合
            }

            return datas
        }

        /**
         * 获取缩放图片
         */
        fun get_scale_bitmap_by_drawable(activity: Activity, @DrawableRes resId: Int): Bitmap {
            // 得到bitmap
            val ori_bitmap = draw_to_bitmap(activity, resId)
            // 获取比例
            val scale = get_scale_from_sreen(activity, ori_bitmap)
            // 缩放图片
            val new_bitmap = set_scale_bitmap(scale, ori_bitmap)
            // 返回
            return new_bitmap!!
        }

        /**
         * Drawable 转换为 Bitmap
         */
        fun draw_to_bitmap(activity: Context, @DrawableRes resId: Int): Bitmap {
            val drawable = ContextCompat.getDrawable(activity, resId)
            val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            return bitmap
        }

        /**
         * 获取原始图与屏幕比例
         */
        fun get_scale_from_sreen(activity: Activity, bitmap: Bitmap): Float {
            // 获取屏幕宽高
            val swh = get_screen_wh(activity)
            val screen_w = swh[0]
            val screen_h = swh[1]
            // 获取图元宽高
            var bitmap_w = bitmap.width.toFloat()
            var bitmap_h = bitmap.height.toFloat()
            // 得到比例
            if (bitmap_w <= 0) return 1f
            return (screen_w / 2) / bitmap_w
        }

        /**
         * 比例缩放图片
         */
        fun set_scale_bitmap(scale: Float, bitmap: Bitmap): Bitmap? {
            if (scale >= 1.0f) return bitmap // 如果不需要缩放 - 直接返回
            val byte_stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byte_stream)
            val inputStream: InputStream = ByteArrayInputStream(byte_stream.toByteArray())
            val option = BitmapFactory.Options()
            option.inTempStorage = ByteArray(100 * 1024)
            option.inPreferredConfig = Bitmap.Config.ARGB_8888
            option.inPurgeable = true
            option.inSampleSize = (1 / scale).toInt()
            option.inInputShareable = true
            return BitmapFactory.decodeStream(inputStream, null, option)
        }

        /**
         * 获取drawable的宽高
         */
        fun get_wh_from_drawable(context: Context, @DrawableRes resid: Int): Array<Float> {
            val option = BitmapFactory.Options()
            BitmapFactory.decodeResource(context.resources, resid, option)
            val outWidth = option.outWidth.toFloat()
            val outHeight = option.outHeight.toFloat()
            return arrayOf(outWidth, outHeight)
        }

        /**
         * 获取副标题模拟数据
         */
        fun getVirtualSubTitles(): ArrayList<SubBean> {
            val datas = ArrayList<SubBean>()
            for (idx in titles.indices) {
                val subBean = SubBean()
                subBean.isCheck = idx == 0 // 默认第一个为true
                subBean.subTitle = titles[idx]
                datas.add(subBean)
            }
            return datas
        }

        /**
         * 获取屏幕宽高
         */
        fun get_screen_wh(activity: Activity): Array<Int> {
            var screen_w = 0
            var screen_h = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val metrics = activity.windowManager.currentWindowMetrics
                val insets = metrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                screen_w = metrics.bounds.width() - insets.left - insets.right
                screen_h = metrics.bounds.height() - insets.top - insets.bottom
            } else {
                val metrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(metrics)
                screen_w = metrics.widthPixels
                screen_h = metrics.heightPixels
            }
            return arrayOf(screen_w, screen_h)
        }

        /**
         * 水平方向移动view
         * @param view 要移动的对象
         * @param distance 偏移距离
         */
        fun move_view_x(view: View, distance: Float) {
            ViewHelper.setTranslationX(view, distance)
        }

        /**
         * 获取模拟主题
         */
        fun getVirtualTheme(context: Context): ArrayList<ThemeBean> {
            val themes = ArrayList<ThemeBean>()
            val drawables = arrayOf(R.drawable.test0, R.drawable.test5, R.drawable.test7, R.drawable.test9)
            val titles = arrayOf("Default theme", "Sicene Theme", "Forrest Theme", "Dark Theme")
            for (idx in drawables.indices) {
                // 提取缩略图
                val imageView = ImageView(context)
                val layoutParams = LinearLayout.LayoutParams(-1, -1)
                imageView.setImageDrawable(ContextCompat.getDrawable(context, drawables[idx]))
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.layoutParams = layoutParams
                // 封装
                val themeBean = ThemeBean()
                themeBean.path = context.getExternalFilesDir(null)?.absolutePath + File.separator + "/skin" + idx + ".apk"
                themeBean.view = imageView
                themeBean.title = titles[idx]
                themeBean.index = idx
                themes.add(themeBean)
            }
            return themes
        }

        /**
         * 设置随机位移动画
         */
        fun setRandomTransAnim(view: View) {
            // 随机一个缩放比例
            val nextInt = Random.nextInt(3, 6)
            val scaleRate: Float = nextInt.toFloat() / 10
            // 随机一个时间
            val randomTime = Random.nextInt(600, 1200)

            val scalAnim = ScaleAnimation(1.0f, scaleRate, 1.0f, scaleRate, 1f, 1f)
            scalAnim.duration = randomTime.toLong()
            scalAnim.interpolator = AccelerateDecelerateInterpolator()
            scalAnim.fillAfter = true
            scalAnim.repeatCount = Animation.INFINITE
            scalAnim.repeatMode = Animation.REVERSE
            scalAnim.startNow()
            view.animation = scalAnim
            view.startAnimation(scalAnim)
        }

        /**
         * 保存主题数据
         */
        fun saveThemeInfo(context: Context, themeBean: ThemeBean) {
            val path = themeBean.path
            val index = themeBean.index
            val title = themeBean.title
            SPUtils.get(context).putString(Cons.THEME_PATH, "$path#$index#$title")
        }

        /**
         * 获取主题数据
         */
        fun getThemeInfo(context: Context): ThemeBean? {
            val infos = SPUtils.get(context).getString(Cons.THEME_PATH, "")
            if (TextUtils.isEmpty(infos)) return null
            val split = infos!!.split("#")
            val themeBean = ThemeBean()
            themeBean.path = split[0]
            themeBean.index = split[1].toInt()
            themeBean.title = split[2]
            return themeBean
        }
    }

}
