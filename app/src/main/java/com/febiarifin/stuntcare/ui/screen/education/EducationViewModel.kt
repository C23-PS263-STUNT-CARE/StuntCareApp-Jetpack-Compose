package com.febiarifin.stuntcare.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.ArticleRepository
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EducationViewModel(
    private val repository: ArticleRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Article>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>>
        get() = _uiState

    fun getAllArticle() {
        viewModelScope.launch {
            repository.getAllArticle()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listArticle ->
                    _uiState.value = UiState.Success(listArticle)
                }
        }
    }
}