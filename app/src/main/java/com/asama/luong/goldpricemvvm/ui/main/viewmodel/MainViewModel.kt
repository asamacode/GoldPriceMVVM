package com.asama.luong.goldpricemvvm.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.asama.luong.goldpricemvvm.data.respository.MetalsRepository
import com.asama.luong.goldpricemvvm.utils.lazyDefered

class MainViewModel( private val metalsRepository: MetalsRepository ) : ViewModel(){

    val metalsData by lazyDefered {

        metalsRepository.getCurrentMetalsData()
    }

}