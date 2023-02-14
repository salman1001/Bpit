package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class AddNewItem (
    @SerializedName("billNumber") val billNumber: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("itemName") val itemName: String?,
    @SerializedName("recievedQuantity") val recievedQuantity: Int?,
    @SerializedName("supplierName") val supplierName: String?,
    @SerializedName("issuedQuantity") val issuedQuantity: Int?,
    @SerializedName("balance") val balance: Int?

)