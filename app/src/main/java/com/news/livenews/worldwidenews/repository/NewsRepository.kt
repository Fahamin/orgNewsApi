package com.fahamin.hiltmvvmkotlincoroutin.repository

import com.fahamin.hiltmvvmkotlincoroutin.api.Api
import com.news.livenews.worldwidenews.Constance
import com.news.livenews.worldwidenews.model.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private var  api: Api) {

    suspend fun getCountryNews(countryCode:String,apiKey:String)  = flow {
        emit(NetworkResult.Loading(true))
        val response = api.getCountryNewsDetails(countryCode,apiKey)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }


    suspend fun getNewsPager(paperCode:String,apiKey:String)  = flow {
        emit(NetworkResult.Loading(true))
        val response = api.getNewsPaperDetails(paperCode,apiKey)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}