package com.example.news_rxjava.data.model.news


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    val `data`: List<News>,
    @SerializedName("pagination")
    val pagination: Pagination?
)