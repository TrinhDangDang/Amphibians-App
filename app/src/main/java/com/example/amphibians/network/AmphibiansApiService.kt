package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET


interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibiansData() : List<AmphibiansData>
            //String
//            List<AmphibiansData>
}

//val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"
//
//val retrofit: Retrofit = Retrofit.Builder()
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
////    .addConverterFactory(ScalarsConverterFactory.create())
//    .baseUrl(baseUrl)
//    .build()
//
//val retrofitService: AmphibiansApiService by lazy {
//    retrofit.create(AmphibiansApiService::class.java)
//}

