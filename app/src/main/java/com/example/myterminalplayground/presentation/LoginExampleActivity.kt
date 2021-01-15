package com.example.myterminalplayground.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import com.example.myterminalplayground.R
import com.papershift.design.component.LoginView
import com.papershift.design.component.LoginViewState
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.filterList

@AndroidEntryPoint
class LoginExampleActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_example)

        val loginView = findViewById<LoginView>(R.id.login_form)
        val progressBar = findViewById<ProgressBar>(R.id.pb_sign_in)



        loginView.setupWithState(
            LoginViewState(
                "vb+test@gmail.com",
                {},
                "VarshaTest$07",
                {},
                true,
                {

                    loginViewModel.signIn("vb+test@gmail.com", "VarshaTest$07")
                },
                {},
                {}
            )
        )

        loginViewModel.signInState.observe(this ){uiState ->
            when(uiState){
                UIState.Loading -> {progressBar.visibility = View.VISIBLE}
                is UIState.Success -> {
                    Log.d("Login", "Success : ${uiState.data}")
                    progressBar.visibility = View.INVISIBLE}
                is UIState.Error -> {progressBar.visibility = View.INVISIBLE}
            }
        }


    }
}