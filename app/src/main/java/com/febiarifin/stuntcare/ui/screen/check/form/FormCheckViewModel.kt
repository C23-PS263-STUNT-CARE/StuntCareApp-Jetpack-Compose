package com.febiarifin.stuntcare.ui.screen.check.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class FormCheckViewModel @Inject constructor(
    private val checkRepository: CheckRepository,
): ViewModel() {

    private val _state = MutableStateFlow(FormCheckState())

    val state = _state.asStateFlow()

    fun onEvent(event: FormCheckEvent){
        when(event){
            is FormCheckEvent.OnCheckStunting -> {
                checkStunting(event.token,event.userId, event.name, event.sex,event.age, event.birth_weight, event.birth_length, event.body_weight, event.body_length, event.asi_ekslusif)
            }
        }
    }

    fun checkStunting(
        token: String,
        userId: String,
        name: String,
        sex: String,
        age: Int,
        birthWeight: Double,
        birthLength: Double,
        bodyWeight: Double,
        bodyLength: Double,
        asiEksklusif: String
    ){
        viewModelScope.launch {
            checkRepository.checkStunting(token,userId, name, sex, age, birthWeight, birthLength, bodyWeight, bodyLength, asiEksklusif).collect{ result ->
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