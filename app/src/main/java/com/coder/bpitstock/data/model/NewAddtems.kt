package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class NewAddtems(
    @SerializedName ("balance")val balance: String,
    @SerializedName("billNumber") val billNumber: String,
    @SerializedName("date") val date: String,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("supplierName") val supplierName: String
)