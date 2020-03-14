package com.asama.luong.goldpricemvvm.data.respository

import androidx.lifecycle.LiveData
import com.asama.luong.goldpricemvvm.data.db.MetalsPriceDao
import com.asama.luong.goldpricemvvm.data.db.entity.Rates
import com.asama.luong.goldpricemvvm.data.network.MetalsPriceDataSource
import com.asama.luong.goldpricemvvm.data.network.response.Metals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MetalsRepositoryImpl(
    private val metalsPriceDataSource: MetalsPriceDataSource,
    private val metalsPriceDao: MetalsPriceDao
) : MetalsRepository {

    init {
        metalsPriceDataSource.apply {
            metalsData.observeForever {metals ->
                saveFetchedDataToDb(metals)
            }
        }
    }

    private fun saveFetchedDataToDb(metals: Metals?) {
        if (metals != null) {
            GlobalScope.launch(Dispatchers.IO) {
                metalsPriceDao.saveData(metals.rates)
            }
        }
    }

    //get current price data from internet
     suspend fun loadMetalsData() {
        metalsPriceDataSource.fetchCurrentPrice("USD", "GBP,JPY,EUR")
    }

    //
    override suspend fun getCurrentMetalsData(): LiveData<Rates> {
        return withContext(Dispatchers.IO) {
            initMetalsData()
            return@withContext metalsPriceDao.getCurrentMetalsData()
        }!!
    }

    //init check if data exist or not
    private suspend fun initMetalsData() {
        val lastData = metalsPriceDao.getCurrentMetalsData()
        if (lastData == null) {
            loadMetalsData()
            return
        }
    }
}