package com.febiarifin.stuntcare.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.auth.AuthRepository
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _state = MutableStateFlow(RegisterState())

    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.OnSignUp -> {
                signUp(name = event.name,email = event.email, password = event.password, confPassword = event.confPassword)
            }
        }
    }

    private fun signUp(name: String, email: String, password: String, confPassword:String){
        viewModelScope.launch {
            authRepository.registerUser(name, email, password, confPassword).collect{ result ->
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