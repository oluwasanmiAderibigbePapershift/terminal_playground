package com.example.myterminalplayground.data

import com.papershift.apiclient.request.authentication.SignInRequest

interface PapershiftRepository {
   suspend fun signIn(email : String, password : String): Result<SignInRequest>
}