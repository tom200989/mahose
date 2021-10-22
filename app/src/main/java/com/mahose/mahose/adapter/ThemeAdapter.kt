package com.mahose.mahose.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.mahose.mahose.bean.ThemeBean

/*
 * Created by 54484 on 10/22/2021.
 */
class ThemeAdapter(var views: ArrayList<ThemeBean>) : PagerAdapter() {

    override fun getCount(): Int {
        return views.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position].view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position].view)
        return views[position].view!!
    }
}
