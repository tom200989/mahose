package com.mahose.mahose.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahose.mahose.R
import com.mahose.mahose.bean.LanguageBean

/*
 * Created by 54484 on 10/25/2021.
 */
class LanguageAdapter(context: Context, languages: ArrayList<LanguageBean>) : RecyclerView.Adapter<LanguageHolder>() {

    var context: Context? = null
    var languages: ArrayList<LanguageBean>? = ArrayList()
    var onSwitchLanguageListener: ((lang_coun: String) -> Unit)? = null

    init {
        this.context = context
        this.languages = languages
    }

    fun notifys(languages: ArrayList<LanguageBean>) {
        this.languages = languages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder {
        return LanguageHolder(LayoutInflater.from(context).inflate(R.layout.item_language, parent, false))
    }

    override fun onBindViewHolder(holder: LanguageHolder, position: Int) {
        val languageBean = languages?.get(position)
        holder.tv_language_title?.text = languageBean?.displayName
        holder.rl_language_check?.visibility = if (languageBean?.isCheck == true) View.VISIBLE else View.GONE
        holder.rl_language?.setOnClickListener {
            // 选中当前
            setChange(position)
            // 刷新ui
            notifyDataSetChanged()
            // 回调
            onSwitchLanguageListener?.invoke(languageBean?.lang_coun ?: "en-US")
        }
    }

    /**
     * 选中当前
     */
    private fun setChange(position: Int) {
        for (i in languages!!.indices) {
            languages!![i].isCheck = i == position
        }
    }

    override fun getItemCount(): Int {
        return languages?.size ?: 0
    }

}
