package com.mahose.mahose.helper

/*
 * Created by 54484 on 10/27/2021.
 */
class LoginHelper : BaseHelper() {

    var onStateListener: ((state: LOGIN_ENUM) -> Unit)? = null

    fun getState() {
        onPrepareListener?.invoke()
        // TODO: 10/27/2021  检查登录状态
        onStateListener?.invoke(LOGIN_ENUM.LOGIN)
        onEndListener?.invoke()
    }

    enum class LOGIN_ENUM {
        LOGIN, LOGOUT, UNREGISTER
    }
}
