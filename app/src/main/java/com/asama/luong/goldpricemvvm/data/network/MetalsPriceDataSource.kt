package com.asama.luong.goldpricemvvm.data.network

import androidx.lifecycle.LiveData
import com.asama.luong.goldpricemvvm.data.network.response.Metals

interface MetalsPriceDataSource {

    val metalsData: LiveData<Metals>

    suspend fun fetchCurrentPrice(base: String, symbols: String)
}