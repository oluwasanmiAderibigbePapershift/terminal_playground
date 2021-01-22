package com.example.myterminalplayground.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myterminalplayground.R
import com.example.myterminalplayground.presentation.UIState.*
import com.papershift.design.component.LoginView
import com.papershift.design.component.LoginViewState
import dagger.hilt.android.AndroidEntryPoint

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
                email = "",
                onEmailChange = {},
                password = "",
                onPasswordChange = {},
                loginButtonEnabled = true,
                onLoginClick = {},
                onRegisterClick = {},
                onForgotPasswordClick = {}
            )
        )

        loginView.updateState {
            onEmailChange = {
                it.let {
                    this.email = it
                }
            }

            onPasswordChange = {
                it.let {
                    this.password = it
                }
            }

            onLoginClick = {
                handleLogin(this.email, this.password)
            }
        }

        loginViewModel.signInState.observe(this) { uiState ->
            when (uiState) {
                is Loading -> progressBar.visibility = View.VISIBLE
                is Error -> progressBar.visibility = View.GONE
                is Success -> {
                    Log.d("Login", "Success : ${uiState.data}")
                    progressBar.visibility = View.GONE
                }

            }
        }
    }

    private fun handleLogin(email: String?, password: String?) {
        loginViewModel.signIn(email.toString(), password.toString())
    }
}