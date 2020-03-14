package com.asama.luong.goldpricemvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_RATES_ID = 0

@Entity(tableName = "Rates")
data class Rates(
    val GBP : Float,
    val JPY: Float,
    val EUR: Float
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_RATES_ID
}