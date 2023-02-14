package com.coder.bpitstock.data.responses

data class IssueSingleItemResponse(
    val __v: Int,
    val _id: String,
    val date: String,
    val department: String,
    val issuedQuantity: Int,
    val itemName: String
)