package com.febiarifin.stuntcare.util

object Constants {
    const val BASE_URL = "https://stuntcare-capstone.df.r.appspot.com/api/"
    const val LOGIN_URL = "login"
    const val REGISTER_URL = "register"
    const val CHECK_POST = "v1/cek/{userId}"
    const val CHECK_HISTORY = "v1/history/{userId}"
    const val CHECK_GET_UPDATE_DELETE = "v1/stunting/{userId}/{checkId}"
    const val GET_INFO = "v1/info"
    const val GET_ALL_ARTICLE_LATEST = "v1/articles?latest=5"
    const val GET_ALL_ARTICLE = "v1/articles"
    const val GET_ARTICLE_BY_ID = "v1/articles/{articleId}"
}