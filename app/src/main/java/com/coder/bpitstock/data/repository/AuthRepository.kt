package com.coder.bpitstock.data.repository

import android.support.v4.media.session.MediaSessionCompat.Token
import com.coder.bpitstock.data.UserPref
import com.coder.bpitstock.data.model.RegisterModel
import com.coder.bpitstock.data.model.User
import com.coder.bpitstock.data.network.AuthApi

class AuthRepository(private val api: AuthApi,private val pref:UserPref): BaseRepository(){
   suspend fun  login(email: String,password:String)=safeApiCall {
      val user= User(email,password)
      api.login(user)}
   suspend fun saveAuthToken(token: String){
      pref.saveToken(token)
   }
   suspend fun  register(name:String,email: String,password:String,phone:String)=safeApiCall {
      val registerModel= RegisterModel(name,email,password,phone)
      api.register(registerModel)}

}