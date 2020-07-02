package com.algro.resume.di.core

import android.app.Activity
import androidx.navigation.NavController
import com.algro.resume.view.core.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    fun navController(activity : Activity) : NavController = (activity as MainActivity).navController

}