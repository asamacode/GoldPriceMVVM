package com.asama.luong.goldpricemvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asama.luong.goldpricemvvm.data.db.entity.CURRENT_RATES_ID
import com.asama.luong.goldpricemvvm.data.db.entity.Rates

@Dao
interface MetalsPriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(rates : Rates)

    @Query("SELECT * FROM Rates WHERE id = $CURRENT_RATES_ID")
    fun getCurrentMetalsData() : LiveData<Rates>
}