package com.example.myterminalplayground.presentation

import androidx.annotation.StringRes

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data : T) : UIState<T>()
    data class Error<T>(@StringRes val error : Int) : UIState<T>()
}

