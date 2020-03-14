package com.asama.luong.goldpricemvvm.data.network.response

import com.asama.luong.goldpricemvvm.data.db.entity.Rates

data class Metals(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Rates
)