package com.febiarifin.stuntcare.ui.screen.detail.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.Repository
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailArticleViewModel(
    private val repository: Repository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Article>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Article>>
        get() = _uiState

    fun getArticleById(articleId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getArticleById(articleId))
        }
    }
}