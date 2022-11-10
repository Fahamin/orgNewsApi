package com.news.livenews.worldwidenews.model

import com.google.gson.annotations.SerializedName
import com.news.livenews.worldwidenews.model.Articles

data class GetNewsView(

    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf()

)