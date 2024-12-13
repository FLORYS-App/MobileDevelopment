package com.example.florys_app.data.response

data class CheckInResponse(
    val message: String,
    val checkInId: String
)

data class CheckInCountResponse(
    val username: String,
    val email: String,
    val checkInCount: Int
)




