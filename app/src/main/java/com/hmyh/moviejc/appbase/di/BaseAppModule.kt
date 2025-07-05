package com.hmyh.moviejc.appbase.di

import com.hmyh.moviejc.appbase.DefaultDispatcherProvider
import com.hmyh.moviejc.domain.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseAppModule {

    @Binds
    abstract fun dispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider

}