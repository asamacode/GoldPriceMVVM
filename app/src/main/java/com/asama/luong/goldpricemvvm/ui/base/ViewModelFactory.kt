package com.asama.luong.goldpricemvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asama.luong.goldpricemvvm.data.respository.MetalsRepository
import com.asama.luong.goldpricemvvm.ui.main.viewmodel.MainViewModel

class ViewModelFactory(
    private val metalsRepository: MetalsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(metalsRepository) as T
        }
        throw IllegalArgumentException("Unkown class name")
    }
}