package com.hmyh.moviejc.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.hmyh.moviejc.network.config.SerializeNullsFactory
import com.hmyh.moviejc.network.extension.BASE_URL
import com.hmyh.moviejc.network.interceptor.HeaderInterceptor
import com.hmyh.moviejc.network.interceptor.NetworkExceptionInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by H.M.Y.H on 2025/07/05
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECT_TIMEOUT = 40L
    private const val READ_TIMEOUT = 40L
    private const val WRITE_TIMEOUT = 40L

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggerInterceptor = HttpLoggingInterceptor()

        loggerInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return loggerInterceptor
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .build()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        chuckerCollector: ChuckerInterceptor,
        loggerInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        networkExceptionInterceptor: NetworkExceptionInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(networkExceptionInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggerInterceptor)
            .addInterceptor(chuckerCollector)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(SerializeNullsFactory())
            .build()
        val baseUrl = BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


}