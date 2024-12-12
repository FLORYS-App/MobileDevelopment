package com.example.florys_app.remote

import com.example.florys_app.data.request.CheckInRequest
import com.example.florys_app.data.response.CheckInCountResponse
import com.example.florys_app.data.response.CheckInResponse
import com.example.florys_app.data.response.FileResponse
import com.example.florys_app.ui.account.LoginRequest
import com.example.florys_app.ui.account.RegisterRequest
import com.example.florys_app.data.response.LoginResponse
import com.example.florys_app.data.response.RegisterResponse
import com.example.florys_app.data.response.UploadResponse
import com.example.florys_app.data.response.WeatherResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

import retrofit2.http.*

interface ApiInterface {
        @POST("register")
        fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

        @POST("login")
        fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

        @POST("checkin")
        fun checkInUser(@Body request: CheckInRequest): Call<CheckInResponse>

        @GET("checkin/{username}")
        fun getCheckInCount(@Path("username") username: String): Call<CheckInCountResponse>

        @Multipart
        @POST("upload")
        fun uploadImage(@Part image: MultipartBody.Part): Call<UploadResponse>

        @GET("files")
        fun getFileList(): Call<List<FileResponse>>

        @GET("files/{filename}")
        fun getFile(@Path("filename") filename: String): Call<FileResponse>

        @GET("weather")
        fun getWeather(@Query("city") city: String, @Query("appid") apiKey: String): Call<WeatherResponse>
}


/*
interface ApiInterface {
        @POST("register")
        fun registerUser(
                @Body request: RegisterRequest
        ): Call<RegisterResponse>

        @POST("login")
        fun loginUser(
                @Body request: LoginRequest
        ): Call<LoginResponse>

        @POST("checkin")
        fun checkInUser(@Body request: CheckInRequest): Call<CheckInResponse>

        @GET("checkin/{username}")
        fun getCheckInCount(@Path("username") username: String): Call<CheckInCountResponse>

        @POST("upload")
        @Multipart
        fun uploadImage(@Part image: MultipartBody.Part): Call<UploadResponse>

        @GET("files")
        fun getFileList(): Call<List<FileResponse>>

        @GET("files/{filename}")
        fun getFile(@Path("filename") filename: String): Call<FileResponse>

        @GET("weather")
        fun getWeather(@Query("city") city: String, @Query("appid") apiKey: String): Call<WeatherResponse>
    }
}
*/