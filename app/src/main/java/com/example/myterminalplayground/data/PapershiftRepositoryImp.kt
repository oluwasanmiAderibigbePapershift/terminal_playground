package com.example.myterminalplayground.data

import com.papershift.apiclient.PapershiftEndpoint
import com.papershift.apiclient.microya.ApiProvider
import com.papershift.apiclient.request.authentication.SignInRequest
import com.papershift.apiclient.request.authentication.UserRequest

class PapershiftRepositoryImp : PapershiftRepository {
    val apiProvider = ApiProvider()
    override suspend fun signIn(email: String, password: String): Result<SignInRequest> {
        val signInRequest = SignInRequest(UserRequest(email, password))
        return apiProvider.performRequest(
            endpoint = PapershiftEndpoint.SignIn(
                signInRequest
            )
        )

    }
}