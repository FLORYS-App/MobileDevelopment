package com.example.florys_app.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private const val BASE_URL = "https://florysmd-708756382552.asia-southeast2.run.app/"
//https://story-api.dicoding.dev/v1/
//https://florysmd-708756382552.asia-southeast2.run.app/
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}