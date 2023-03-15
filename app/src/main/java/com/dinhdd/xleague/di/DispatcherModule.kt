package com.dinhdd.xleague.di

import com.dinhdd.xleague.dispatcher.DefaultDispatcherProvider
import com.dinhdd.xleague.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    abstract fun getDispatcher(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}