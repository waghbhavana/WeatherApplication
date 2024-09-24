package com.bhavanawagh.weatherapplication.di.module

import android.content.Context
import com.bhavanawagh.weatherapplication.data.api.NetworkServices
import com.bhavanawagh.weatherapplication.di.BaseUrl
import com.bhavanawagh.weatherapplication.di.WeatherApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://api.openweathermap.org/"

   //Generated API key from OpenWeather.com
    @WeatherApiKey
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

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext appContext: Context,
        loggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggerInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
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