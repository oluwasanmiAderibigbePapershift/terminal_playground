package com.example.myterminalplayground.domain.repository.authenication

import com.example.myterminalplayground.domain.model.authentication.SignInModel

interface AuthenticationRepository {
    suspend fun sign(email : String, password : String) : Result<SignInModel>
}