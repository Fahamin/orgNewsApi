package com.fahamin.hiltmvvmkotlincoroutin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahamin.hiltmvvmkotlincoroutin.repository.NewsRepository
import com.news.livenews.worldwidenews.Constance
import com.news.livenews.worldwidenews.model.GetNewsView
import com.news.livenews.worldwidenews.model.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private var newsRepository: NewsRepository,
) : ViewModel() {

    var countryNews = MutableLiveData<NetworkResult<Response<GetNewsView>>>()
    var paperNews = MutableLiveData<NetworkResult<Response<GetNewsView>>>()


    init {

     /*   if (countryCode.isNotEmpty()) {
            getCountryNews()
        }

        if (paperCode.isNotEmpty()) {
            getPaperNews()
        }
*/

    }

     fun getPaperNews(paperCode:String,apiKey:String) {
        viewModelScope.launch {
            newsRepository.getNewsPager(paperCode, apiKey).collect() {
                paperNews.postValue(it)
            }
        }
    }

     fun getCountryNews(countryCode:String,apiKey:String) {
        viewModelScope.launch {
            newsRepository.getCountryNews(countryCode, apiKey).collect() {
                countryNews.postValue(it)
            }
        }
    }
}