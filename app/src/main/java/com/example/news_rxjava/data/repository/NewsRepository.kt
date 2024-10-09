package com.example.news_rxjava.data.repository

import com.example.news_rxjava.data.model.news.NewsResponse
import com.example.news_rxjava.data.source.remote.ApiManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiManager: ApiManager
) {

    fun getAllNews(): Single<NewsResponse> {
        return apiManager.getAllNews().subscribeOn(Schedulers.io())
    }

}