package com.bhavanawagh.weatherapplication.di.module

import com.bhavanawagh.weatherapplication.data.api.NetworkServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    //Base Url
    @Provides
    fun provideBaseUrl(): String = "https://api.openweathermap.org/"

   //Generated API key from OpenWeather.com
    @Provides
    fun provideNetworkApiKey(): String = "40120425479a73ac81a2208d0b7dbdc6"

    @Singleton
    @Provides
    fun provideGsonConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideNetworkService(
       baseUrl: String,
        gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient
    )
            : NetworkServices {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkServices::class.java)
    }

}