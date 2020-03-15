package com.asama.luong.goldpricemvvm.data.network

import com.asama.luong.goldpricemvvm.data.network.response.Metals
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY: String = "fuuxyn5j5tabkkvua53blf206gb20j3jxyzvq8uw7k5yopda84hr1tioepvo"

const val BASE_URL: String = "https://metals-api.com/api/"

interface APIGoldPriceService {

    @GET("latest")
    fun getCurrentPrice(
        @Query("base") currency: String,
        @Query("symbols") symbols: String
    ): Deferred<Metals>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): APIGoldPriceService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIGoldPriceService::class.java)
        }
    }
}