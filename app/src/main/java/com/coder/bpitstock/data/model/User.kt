package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
)