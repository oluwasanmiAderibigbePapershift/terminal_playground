package com.example.myterminalplayground.di

import com.example.myterminalplayground.data.repository.PaperAuthRepository
import com.example.myterminalplayground.domain.repository.authenication.AuthenticationRepository
import com.example.myterminalplayground.domain.usecase.authentication.SignInUseCase
import com.papershift.apiclient.microya.ApiProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPaperShiftAuthRepository(apiProvider: ApiProvider): PaperAuthRepository {
        return PaperAuthRepository(apiProvider)
    }

    @Provides
    @Singleton
    fun providesSignUseCase(authenticationRepository: AuthenticationRepository): SignInUseCase {
        return SignInUseCase(authenticationRepository)
    }



    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {

        @Binds
        @Singleton
        abstract fun bindsPaperShiftAuthRepository(paperAuthRepository: PaperAuthRepository): AuthenticationRepository

    }

}