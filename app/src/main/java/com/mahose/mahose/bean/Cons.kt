package com.mahose.mahose.bean

/*
 * Created by 54484 on 10/22/2021.
 */
class Cons {
    companion object {
        @JvmStatic
        var THEME_PATH: String = "THEME_PATH" // 主题地址
        
        @JvmStatic
        var CURRENT_LANG = "CURRENT_LANG" // 选中的语言 en-US,zh-CN

        var COUNTRYS = arrayOf(// 支持的语言
            LANGUAGES.ENGLISH + "-US",
            LANGUAGES.CHINA + "-CN"
        )
    }

    // 支持的语言
    class LANGUAGES {

        companion object {
            @JvmStatic
            var ENGLISH = "en"

            @JvmStatic
            var CHINA = "zh"
        }
    }
}
