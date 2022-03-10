package com.junka.mapache.di

import com.junka.mapache.BuildConfig
import com.junka.mapache.data.remote.AniService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("endpoint")
    fun providesEndpoint(): String = "https://api.aniapi.com/v1/"

    @Provides
    @Named("private_key")
    fun providesPrivateKey(): String = BuildConfig.PRIVATE_KEY

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @Named("tokenInterceptor")
    fun providesTokenInterceptor(
        @Named("private_key") privateKey: String
    ) = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer $privateKey")
                .build()
        )
    }

    @Provides
    @Singleton
    fun providesClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("tokenInterceptor") tokenInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .apply {
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                connectTimeout(30, TimeUnit.SECONDS)
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(tokenInterceptor)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("endpoint") endpoint: String,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAniApiService(
        retrofit: Retrofit
    ): AniService {
        return retrofit.create(AniService::class.java)
    }

}