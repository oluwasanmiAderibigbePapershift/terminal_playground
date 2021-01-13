package com.example.myterminalplayground

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myterminalplayground.data.PapershiftRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainAcitivityViewModel : ViewModel() {

    val repo = PapershiftRepositoryImp()

    fun signIn(email : String, password : String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.signIn(email, password).onSuccess {
                Log.d("Test", it.toString())
            }.onFailure {
                Log.d("Test", it.toString())
            }
        }
    }
}