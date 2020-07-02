package com.algro.resume.di.imgflip

import com.algro.resume.remote.ImgFlip
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ImgFlipModule {

    @Singleton
    @Provides
    fun remote(retrofit: Retrofit) = retrofit.create(ImgFlip::class.java)

}