package com.coder.bpitstock.data.responses

data class RegisterResponse(
    val __v: Int,
    val _id: String,
    val email: String,
    val name: String,
    val password: String,
    val phone: String
)