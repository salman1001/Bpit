package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class DeleteItem (
    @SerializedName("id") val itemName: String?,

)