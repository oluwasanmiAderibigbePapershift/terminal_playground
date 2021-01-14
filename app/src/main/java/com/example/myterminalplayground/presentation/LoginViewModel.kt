package com.example.myterminalplayground.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myterminalplayground.data.NetworkState
import com.example.myterminalplayground.domain.model.SignInModel
import com.example.myterminalplayground.domain.usecase.authentication.SignInUseCase
import com.papershift.apiclient.microya.JsonApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    //Convert to stateflow?
    private val _signInState = MutableLiveData<UIState<SignInModel>>()
    val signInState get() = _signInState as LiveData<UIState<SignInModel>>

    fun signIn(email: String, password: String) {
        //Inject dispatcher
        viewModelScope.launch(Dispatchers.IO) {
            signInUseCase.execute(email, password).also { networkState ->
                Log.d("Login:", "Net work : ${networkState::class.simpleName}")
                when (networkState) {
                    NetworkState.Loading ->_signInState.postValue(UIState.Loading)
                    is NetworkState.Success -> _signInState.postValue(UIState.Success(networkState.data))
                    is NetworkState.Error -> {
                        when (networkState.jsonApiException) {
                            JsonApiException.NoResponseReceived -> {
                            }
                            JsonApiException.NoDataInResponse -> {
                            }
                            is JsonApiException.ResponseDataConversionFailed -> {
                            }
                            is JsonApiException.ClientError -> {
                            }
                            is JsonApiException.ServerError -> {
                                Log.d("Login", networkState.jsonApiException.message.toString())}
                            is JsonApiException.UnexpectedStatusCode -> {
                            }
                            is JsonApiException.UnexpectedException -> {
                            }
                        }

                    }
                }
            }
        }

    }
}