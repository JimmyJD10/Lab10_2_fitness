package com.example.fitnessappwger

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.fitnessappwger.data.ExerciseResponse

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WgerApi by lazy {
        retrofit.create(WgerApi::class.java)
    }
}

interface WgerApi {
    @GET("exercise/")
    suspend fun getExercises(): retrofit2.Response<ExerciseResponse>
}
