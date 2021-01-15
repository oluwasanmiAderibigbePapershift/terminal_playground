package com.example.myterminalplayground.di

import com.example.myterminalplayground.data.mapper.PapershiftTerminalMapper
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
    fun providesPaperShiftAuthRepository(apiProvider: ApiProvider, terminalMapper: PapershiftTerminalMapper): PaperAuthRepository {
        return PaperAuthRepository(apiProvider, terminalMapper)
    }

    @Provides
    @Singleton
    fun providesSignUseCase(authenticationRepository: AuthenticationRepository): SignInUseCase {
        return SignInUseCase(authenticationRepository)
    }


    @Provides
    @Singleton
    fun providesTerminalMapper() : PapershiftTerminalMapper{
        return PapershiftTerminalMapper()
    }


    @Module
    @InstallIn(ApplicationComponent::class)
    abstract class BindsModule {
        @Binds
        @Singleton
        abstract fun bindsPaperShiftAuthRepository(paperAuthRepository: PaperAuthRepository): AuthenticationRepository
    }

}