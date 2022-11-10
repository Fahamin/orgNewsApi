package com.fahamin.hiltmvvmkotlincoroutin.api

import com.news.livenews.worldwidenews.model.GetNewsView
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface Api {

    @GET
    suspend fun getNumberInfo(@Url url: String?): Response<String?>?

    @GET("top-headlines")
    suspend fun getCountryNewsDetails(
        @Query("country") country: String,
        @Query("apiKey") keyID: String
    ): Response<GetNewsView>

    @GET("top-headlines")
    suspend fun getNewsPaperDetails(
        @Query("sources") source: String,
        @Query("apiKey") keyID: String
    ): Response<GetNewsView>

}