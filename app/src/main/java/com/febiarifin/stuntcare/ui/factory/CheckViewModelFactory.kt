package com.febiarifin.stuntcare.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febiarifin.stuntcare.data.CheckRepository
import com.febiarifin.stuntcare.ui.screen.check.CheckViewModel
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckViewModel

class CheckViewModelFactory(private val repository: CheckRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckViewModel::class.java)){
            return CheckViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailCheckViewModel::class.java)){
            return DetailCheckViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}