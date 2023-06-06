package com.febiarifin.stuntcare.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febiarifin.stuntcare.data.ArticleRepository
import com.febiarifin.stuntcare.ui.screen.detail.article.DetailArticleViewModel
import com.febiarifin.stuntcare.ui.screen.education.EducationViewModel

class ArticleViewModelFactory(private val repository: ArticleRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EducationViewModel::class.java)){
            return EducationViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailArticleViewModel::class.java)){
            return DetailArticleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}