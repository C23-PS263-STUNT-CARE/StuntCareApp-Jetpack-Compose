package com.febiarifin.stuntcare.ui.screen.detail.check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.Repository
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.screen.auth.login.LoginState
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCheckViewModel @Inject constructor(
    private val checkRepository: CheckRepository
): ViewModel() {

    private val _state = MutableStateFlow(DetailCheckState())

    val state = _state.asStateFlow()

    fun getStuntingById(token: String, userId: String, checkId: Int){
        viewModelScope.launch {
            checkRepository.getStuntingById(token, userId, checkId).collect{ result ->
                when(result){
                    is Result.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            it.copy(loading = false, errorMessage = result.message)
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

    fun deleteStuntingById(token: String, userId: String, checkId: Int){
        viewModelScope.launch {
            checkRepository.deleteStuntingById(token, userId, checkId).collect{ result ->
                when(result){
                    is Result.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            it.copy(loading = false, errorMessage = result.message)
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(data = null, loading = false)
                        }
                    }
                }
            }
        }
    }

}