package com.asama.luong.goldpricemvvm.data.network.response

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("info")
    val info: String
)
