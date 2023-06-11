package com.febiarifin.stuntcare.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febiarifin.stuntcare.data.repository.Repository
import com.febiarifin.stuntcare.data.repository.check.CheckRepository
import com.febiarifin.stuntcare.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkRepository: CheckRepository,
): ViewModel() {

    private val _stateInfo = MutableStateFlow(InfoState())
    val stateInfo = _stateInfo.asStateFlow()

    fun getAllInfo(token: String){
        viewModelScope.launch {
            checkRepository.getAllInfo(token).collect{result ->
                when(result){
                    is Result.Loading -> {
                        _stateInfo.update {
                            it.copy(loading = true)
                        }
                    }
                    is Result.Error -> {
                        _stateInfo.update {
                            it.copy(errorMessage = result.message, loading = false)
                        }
                    }
                    is Result.Success -> {
                        _stateInfo.update {
                            it.copy(data = result.data, loading = false)
                        }
                    }
                }
            }
        }
    }

}