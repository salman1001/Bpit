package com.coder.bpitstock.data.responses

data class SunDayAllitemsItem(
    val __v: Int,
    val _id: String,
    val balance: Int,
    val billNumber: Long,
    val createdAt: String,
    val date: String,
    val items: List<ItemXX>,
    val supplierName: String,
    val updatedAt: String
)