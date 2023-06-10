package com.febiarifin.stuntcare.ui.screen.check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.Repository
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckViewModel @Inject constructor(
//    private val repository: Repository,
    private val checkRepository: CheckRepository,
): ViewModel() {
    private val _state = MutableStateFlow(CheckState())

    val state = _state.asStateFlow()

    fun getAllCheckHistory(token: String, userId: String){
        viewModelScope.launch {
            checkRepository.getAllCheckHistory(token, userId).collect{result ->
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