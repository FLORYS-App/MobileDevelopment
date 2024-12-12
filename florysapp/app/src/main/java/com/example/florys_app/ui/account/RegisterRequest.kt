package com.example.florys_app.ui.account

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)

/* old
* data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)*/