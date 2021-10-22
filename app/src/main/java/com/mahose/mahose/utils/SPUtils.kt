package com.rootmastatic.rootmastatic.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/*
 * Created by Administrator on 2021/5/24.
 */
@SuppressLint("StaticFieldLeak","CommitPrefEdits")
open class SPUtils {

    object SpManager {
        internal val shareUtils = SPUtils()
    }

    companion object {

        var SP_NAME = "MAHOSE"
        val MODE = Context.MODE_PRIVATE

        var context: Context? = null
        var sp: SharedPreferences? = null
        var ed: SharedPreferences.Editor? = null

        internal fun get(cts: Context): SPUtils {
            context = cts
            sp = context?.getSharedPreferences(SP_NAME, MODE)
            ed = sp?.edit()
            return SpManager.shareUtils
        }
    }

    fun get_sp_name(): String {
        return SP_NAME
    }

    fun set_sp_name(sp_name: String): SPUtils {
        SP_NAME = sp_name
        return SpManager.shareUtils
    }

    fun putInt(key: String, value: Int) {
        ed?.putInt(key, value)?.apply()
    }

    fun putFloat(key: String, value: Float) {
        ed?.putFloat(key, value)?.apply()
    }

    fun putLong(key: String, value: Long) {
        ed?.putLong(key, value)?.apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        ed?.putBoolean(key, value)?.apply()
    }

    fun putString(key: String, value: String) {
        ed?.putString(key, value)?.apply()
    }

    fun getInt(key: String, default: Int): Int? {
        return sp?.getInt(key, default)
    }

    fun getFloat(key: String, default: Float): Float? {
        return sp?.getFloat(key, default)
    }

    fun getLong(key: String, default: Long): Long? {
        return sp?.getLong(key, default)
    }

    fun getBoolean(key: String, default: Boolean): Boolean? {
        return sp?.getBoolean(key, default)
    }

    fun getString(key: String, default: String): String? {
        return sp?.getString(key, default)
    }

    fun isKeyExist(key: String): Boolean? {
        return sp?.contains(key)
    }
}

