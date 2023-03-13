package com.dinhdd.xleague.di

import com.dinhdd.xleague.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {
    @Provides
    @Named("BASE_URL")
    fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}