package com.febiarifin.stuntcare.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.auth.AuthRepository
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())

    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnSignIn -> {
                signIn(email = event.email, password = event.password)
            }

//            is LoginEvent.OnSignInResult -> {
//                _state.update { it.copy(
//                    = result.data != null,
//                    signInError = result.errorMessage
//                ) }
//            }
        }
    }

    private fun signIn(email: String, password: String){
        viewModelScope.launch {
            authRepository.loginUser(email, password).collect{ result ->
                when(result){
                    is Result.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            it.copy(errorMessage = result.message, loading = false)
                        }
                    }
                    is Result.Success -> {

                        _state.update {
                            it.copy(data = result.data, loading = false)
                        }
                    }
                }
            }
        }
    }

}