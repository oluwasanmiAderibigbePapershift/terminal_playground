package com.example.myterminalplayground.di

import com.papershift.apiclient.microya.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesPaperShiftAPIProvider() : ApiProvider{
        return ApiProvider()
    }
}