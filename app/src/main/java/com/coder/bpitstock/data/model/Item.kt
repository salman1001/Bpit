package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("itemName") val itemName: String,
    @SerializedName("recievedQuantity") val recievedQuantity: String
)