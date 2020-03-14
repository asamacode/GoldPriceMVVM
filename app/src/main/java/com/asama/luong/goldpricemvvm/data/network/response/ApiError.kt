package com.asama.luong.goldpricemvvm.data.network.response

import com.google.gson.annotations.SerializedName

data class ApiError(
    val success: Boolean,
    @SerializedName("error")
    val error: ErrorResponse
)