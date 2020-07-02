package com.algro.resume.di.core

import android.app.Application
import androidx.room.Room
import com.algro.resume.core.AlgroDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    private val loggingInterceptor = HttpLoggingInterceptor()
        .also { it.level = HttpLoggingInterceptor.Level.BODY }

    private const val BASE_URL = "https://api.imgflip.com/"

    @Singleton

    @Provides
    fun moshi() : Moshi {
        return Moshi.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun okHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun retrofit(moshi: Moshi, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    moshi
                )
            )
            .build()
    }


    @Singleton
    @Provides
    fun database(application : Application) : AlgroDatabase {
        return Room.databaseBuilder(application, AlgroDatabase::class.java, "Algro")
            .fallbackToDestructiveMigration()
            .build()
    }

}