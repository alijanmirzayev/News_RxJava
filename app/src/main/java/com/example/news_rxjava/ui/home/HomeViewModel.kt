package com.example.news_rxjava.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_rxjava.data.model.news.NewsResponse
import com.example.news_rxjava.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse> get() = _news

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        val disposable = newsRepository.getAllNews()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _loading.postValue(true)
            }
            .subscribe(
                { response ->
                    _news.value = response
                    _loading.value = false
                },
                { throwable ->
                    _error.value = throwable.message
                    _loading.value = false
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}