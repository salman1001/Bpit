package com.coder.bpitstock.data.network

import com.coder.bpitstock.data.model.RegisterModel
import com.coder.bpitstock.data.model.User
import com.coder.bpitstock.data.responses.LoginResponse
import com.coder.bpitstock.data.responses.RegisterResponse
import retrofit2.http.*

interface AuthApi {
   // @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body user: User
//        @Field("email") email:String,
//        @Field("password") password:String
    ): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("signup")
    suspend fun register(
        @Body regUser: RegisterModel
//        @Field("email") email:String,
//        @Field("password") password:String
    ): RegisterResponse


}