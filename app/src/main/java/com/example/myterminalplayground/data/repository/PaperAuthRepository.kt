package com.example.myterminalplayground.data.repository

import com.example.myterminalplayground.domain.model.SignInModel
import com.example.myterminalplayground.domain.repository.authenication.AuthenticationRepository
import com.papershift.apiclient.PapershiftEndpoint
import com.papershift.apiclient.microya.ApiProvider
import com.papershift.apiclient.request.authentication.SignInRequest
import com.papershift.apiclient.request.shared.ApiRequest
import com.papershift.apiclient.request.shared.DataRequest
import com.papershift.apiclient.response.authentication.SignInResponse
import com.papershift.apiclient.response.shared.ApiResponse

class PaperAuthRepository(private val apiProvider: ApiProvider) : AuthenticationRepository {

    override suspend fun sign(email: String, password: String): Result<SignInModel> {
        val signInRequest = SignInRequest(email, password)
        val signInEndpoint = PapershiftEndpoint.SignIn(
            body = ApiRequest(
                DataRequest(
                    type = "user",
                    attributes = signInRequest
                )
            )
        )
        return apiProvider.performRequest<ApiResponse<SignInResponse>>(signInEndpoint)
            .map { response: ApiResponse<SignInResponse> ->
                //Move this to mapping class or use extension functions?
                with(response.data.attributes) {
                    SignInModel(
                        email = email,
                        userName = username,
                        id = accountId
                    )
                }
            }
    }
}