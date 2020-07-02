package com.algro.resume.di.core

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.migration.DisableInstallInCheck

@InstallIn(ApplicationComponent::class)
@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
@DisableInstallInCheck
interface AssistedInjectModule