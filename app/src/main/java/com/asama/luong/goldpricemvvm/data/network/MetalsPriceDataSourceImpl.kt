package com.asama.luong.goldpricemvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asama.luong.goldpricemvvm.data.network.response.Metals
import com.asama.luong.goldpricemvvm.utils.NoConnectivityException

class MetalsPriceDataSourceImpl(
    private val apiGoldPriceService: APIGoldPriceService
) : MetalsPriceDataSource {

    private val _metalsData = MutableLiveData<Metals>()

    override val metalsData: LiveData<Metals>
        get() = _metalsData

    override suspend fun fetchCurrentPrice(base: String, symbols: String) {

        try {
            val fetchedData = apiGoldPriceService.getCurrentPrice(base, symbols).await()
            _metalsData.postValue(fetchedData)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}