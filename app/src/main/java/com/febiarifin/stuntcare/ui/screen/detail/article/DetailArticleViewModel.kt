package com.febiarifin.stuntcare.ui.screen.detail.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val checkRepository: CheckRepository
): ViewModel() {

    private val _state = MutableStateFlow(DetailArticleState())
    val state = _state.asStateFlow()

    fun getArticleById(token: String, articleId: Int){
        viewModelScope.launch {
            checkRepository.getArticleById(token, articleId).collect{ result ->
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
                            it.copy(loading = false, data = result.data)
                        }
                    }
                }
            }
        }
    }

}