package com.febiarifin.stuntcare.ui.screen.detail.check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.CheckRepository
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCheckViewModel(
    private val repository: CheckRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Check>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Check>>
        get() = _uiState

    fun getCheckById(checkId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCheckById(checkId))
        }
    }
}