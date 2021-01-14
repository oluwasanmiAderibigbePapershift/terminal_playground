package com.example.myterminalplayground.data

import com.papershift.apiclient.microya.JsonApiException

sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Success<T>(val data : T) : NetworkState<T>()
    data class Error<T>(val jsonApiException: JsonApiException) : NetworkState<T>()
}

