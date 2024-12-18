package com.example.florys_app.data.response

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val email: String,
    val username: String,
    val loginResult: LoginResult
)

data class LoginResult(
    val userId: String,
    val username: String,
    val token: String,
    val email: String,
    val password: String
)