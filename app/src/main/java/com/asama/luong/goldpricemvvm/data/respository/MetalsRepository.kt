package com.asama.luong.goldpricemvvm.data.respository

import androidx.lifecycle.LiveData
import com.asama.luong.goldpricemvvm.data.db.entity.Rates

interface MetalsRepository {

    suspend fun getCurrentMetalsData(): LiveData<Rates>
}