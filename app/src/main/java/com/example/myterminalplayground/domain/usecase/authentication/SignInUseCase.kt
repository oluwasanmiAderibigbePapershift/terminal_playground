package com.example.myterminalplayground.domain.usecase.authentication

import com.example.myterminalplayground.data.NetworkState
import com.example.myterminalplayground.domain.model.authentication.SignInModel
import com.example.myterminalplayground.domain.repository.authenication.AuthenticationRepository

import com.papershift.apiclient.microya.JsonApiException
import java.util.concurrent.CancellationException

class SignInUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend fun execute(email : String, password : String) : NetworkState<SignInModel> {
         return authenticationRepository.sign(email, password).fold(
             onSuccess = { data -> NetworkState.Success(data) },
             onFailure = { throwable ->
                 when (throwable) {
                     is CancellationException -> {
                         throw throwable
                     }
                     else -> {
                         NetworkState.Error(throwable as JsonApiException)
                     }
                 }
             })
    }
}