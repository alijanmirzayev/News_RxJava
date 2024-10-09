package com.example.news_rxjava.data.source.remote

import com.example.news_rxjava.data.model.news.NewsResponse
import com.example.news_rxjava.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {

    @GET("news")
    fun getAllNews(
        @Query("access_key") key: String = Constants.API_KEY
    ) : Single<NewsResponse>

}