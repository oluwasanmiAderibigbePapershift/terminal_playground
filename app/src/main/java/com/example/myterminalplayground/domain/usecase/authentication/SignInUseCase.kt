package com.example.myterminalplayground.domain.usecase.authentication

import com.example.myterminalplayground.data.NetworkState
import com.example.myterminalplayground.domain.model.SignInModel
import com.example.myterminalplayground.domain.repository.authenication.AuthenticationRepository

import com.papershift.apiclient.microya.JsonApiException
import java.util.concurrent.CancellationException

class SignInUseCase(private val authenticationRepository: AuthenticationRepository) {

    //todo do i need a uistate. i could simple make the viewmodel cleaner by doing the jsonAPI Exception convertion here
    suspend fun execute(email : String, password : String) : NetworkState<SignInModel> {

         return authenticationRepository.sign(email, password).fold({ data ->
              NetworkState.Success(data)
          }, { throwable ->
              if(throwable is CancellationException){
                  throw throwable
              }else{
                  NetworkState.Error(throwable as JsonApiException)
              }
         })
    }
}