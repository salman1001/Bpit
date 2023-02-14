package com.coder.bpitstock.data.responses

data class LoginResponse(
    val email: String,
    val name: String,
    val token: String
)