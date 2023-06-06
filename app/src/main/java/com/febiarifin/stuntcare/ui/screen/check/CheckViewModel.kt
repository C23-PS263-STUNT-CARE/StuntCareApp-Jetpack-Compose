package com.febiarifin.stuntcare.ui.screen.check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.Repository
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CheckViewModel(
    private val repository: Repository,
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Check>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Check>>>
        get() = _uiState

    fun getAllCheck() {
        viewModelScope.launch {
            repository.getAllCheck()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listCheck ->
                    _uiState.value = UiState.Success(listCheck)
                }
        }
    }
}