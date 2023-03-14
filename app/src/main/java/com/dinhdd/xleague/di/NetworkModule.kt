package com.dinhdd.xleague.di

import com.dinhdd.data.remote.service.MatchServices
import com.dinhdd.data.remote.service.TeamServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        private val READ_TIME_OUT = 30L
        private val READ_TIME_OUT_UNIT = TimeUnit.SECONDS
        private val CONNECT_TIME_OUT = 30L
        private val CONNECT_TIME_OUT_UNIT = TimeUnit.SECONDS
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, READ_TIME_OUT_UNIT)
            .connectTimeout(CONNECT_TIME_OUT, CONNECT_TIME_OUT_UNIT)
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(@Named("BASE_URL") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun getTeamService(retrofit: Retrofit): TeamServices = retrofit.create()

    @Provides
    fun getMatchService(retrofit: Retrofit): MatchServices = retrofit.create()
}