package com.coder.bpitstock.data.network

import com.coder.bpitstock.data.model.*
import com.coder.bpitstock.data.responses.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {



    @GET("added-items")
    suspend fun getItems():SunDayAllitems


    @GET("issued-items")
    suspend fun getIssuedItems():IssuedItems




    @Headers("Content-Type: application/json")
    @POST("add-item")
    suspend fun addItem(@Body addNewItem: NewAddtems):NewAddItemRespose



    @Headers("Content-Type: application/json")
    @POST("issue-item")
    suspend fun issue(@Body issueItem: IssueItem):IssueSingleItemResponse



    @Headers("Content-Type: application/json")
    @POST("delete")
    suspend fun delete(@Body delete: DeleteItem):DeleteResponse








}