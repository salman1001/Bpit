package com.coder.bpitstock.data.responses

data class AdditemResponse(
    val __v: Int,
    val _id: String,
    val balance: Int,
    val billNumber: Int,
    val date: String,
    val issuedQuantity: Int,
    val itemName: String,
    val recievedQuantity: Int,
    val supplierName: String
)