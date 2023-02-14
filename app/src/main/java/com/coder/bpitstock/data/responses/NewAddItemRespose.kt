package com.coder.bpitstock.data.responses

data class NewAddItemRespose(
    val __v: Int,
    val _id: String,
    val balance: Int,
    val billNumber: Int,
    val createdAt: String,
    val date: String,
    val items: List<ItemX>,
    val supplierName: String,
    val updatedAt: String
)