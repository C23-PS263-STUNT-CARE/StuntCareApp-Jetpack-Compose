package com.febiarifin.stuntcare.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febiarifin.stuntcare.data.repository.Repository
import com.febiarifin.stuntcare.ui.screen.check.CheckViewModel
import com.febiarifin.stuntcare.ui.screen.detail.article.DetailArticleViewModel
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckViewModel
import com.febiarifin.stuntcare.ui.screen.education.EducationViewModel
import com.febiarifin.stuntcare.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CheckViewModel::class.java)){
//            return CheckViewModel(repository) as T
//        }else
//        if (modelClass.isAssignableFrom(DetailCheckViewModel::class.java)) {
//            return DetailCheckViewModel(repository) as T
//        } else
        if (modelClass.isAssignableFrom(EducationViewModel::class.java)) {
            return EducationViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailArticleViewModel::class.java)) {
            return DetailArticleViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}