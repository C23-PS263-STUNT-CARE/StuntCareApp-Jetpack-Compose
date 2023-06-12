package com.febiarifin.stuntcare.ui.screen.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.ui.screen.home.ArticleState
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(
    private val checkRepository: CheckRepository
): ViewModel() {
    private val _state = MutableStateFlow(ArticleState())
    val state = _state.asStateFlow()

    fun getAllArticle(token: String) {
        viewModelScope.launch {
            checkRepository.getAllArticle(token).collect{result ->
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