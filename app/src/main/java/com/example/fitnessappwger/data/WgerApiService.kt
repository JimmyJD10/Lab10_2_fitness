package com.example.fitnessappwger.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WgerApiService {
    @GET("exercise/")
    suspend fun getExercises(): Response<ExerciseResponse>

    @GET("exercise/{id}/")
    suspend fun getExercise(@Path("id") id: Int): Response<Exercise>
}
