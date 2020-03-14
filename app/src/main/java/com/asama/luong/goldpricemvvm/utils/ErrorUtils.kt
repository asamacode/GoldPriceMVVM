package com.asama.luong.goldpricemvvm.utils

import com.asama.luong.goldpricemvvm.data.network.response.ApiError
import com.asama.luong.goldpricemvvm.data.network.response.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

class ErrorUtils {

    companion object {

        fun parseError(response: Response<Any>) : ApiError{

            val gson = Gson()

            val body = response.errorBody().toString()
            if (body != null) {

                return gson.fromJson(body, ApiError::class.java)

            } else {
                return ApiError(success = false, error = ErrorResponse(0,"Unknown", "Can not parse error"))
            }
        }
    }
}