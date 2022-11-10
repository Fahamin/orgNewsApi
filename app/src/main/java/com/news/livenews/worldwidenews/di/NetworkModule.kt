package com.fahamin.hiltmvvmkotlincoroutin.di

import com.fahamin.hiltmvvmkotlincoroutin.api.Api
import com.news.livenews.worldwidenews.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}