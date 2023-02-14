package com.coder.bpitstock.data.model

import com.google.gson.annotations.SerializedName

data class IssueItem (
    @SerializedName("itemName") val itemName: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("department") val department: String?,
    @SerializedName("issuedQuantity") val issuedQuantity: Int?

)