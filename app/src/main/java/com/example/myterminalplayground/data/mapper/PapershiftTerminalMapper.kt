package com.example.myterminalplayground.data.mapper

import com.example.myterminalplayground.domain.model.authentication.SignInModel
import com.papershift.apiclient.response.authentication.SignInResponse
import com.papershift.apiclient.response.shared.ApiResponse

class PapershiftTerminalMapper {

    fun mapSignInResponseToDomainModel(signInResponse: ApiResponse<SignInResponse>) : SignInModel{
        return with(signInResponse.data.attributes) {
            SignInModel(
                email = email,
                userName = username,
                id = accountId
            )
        }
    }
}