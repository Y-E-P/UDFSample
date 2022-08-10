package com.pou.udfsample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pou.udfsample.domain.repo.FruitsRepository

class MainViewModelFactory(private val fruitsRepository: FruitsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(fruitsRepository) as T
    }
}