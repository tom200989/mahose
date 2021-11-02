package com.mahose.mahose.helper

/*
 * Created by 54484 on 10/27/2021.
 */
open class BaseHelper {
    var onPrepareListener:(()-> Unit)?= null
    var onEndListener:(()-> Unit)?= null
}
