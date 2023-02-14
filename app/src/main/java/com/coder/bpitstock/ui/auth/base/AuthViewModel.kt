package com.coder.bpitstock.ui.auth.base

import android.provider.ContactsContract.CommonDataKinds.Email
import android.support.v4.media.session.MediaSessionCompat.Token
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.repository.AuthRepository
import com.coder.bpitstock.data.responses.LoginResponse
import com.coder.bpitstock.data.responses.RegisterResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
):ViewModel(){
    private val _loginResponse:MutableLiveData<Resource<LoginResponse>> =MutableLiveData()
    val loginResponse:LiveData<Resource<LoginResponse>>
    get()=_loginResponse
    fun login(email: String,password:String)=  viewModelScope.launch {
       _loginResponse.value=repository.login(email,password)
    }
    fun  saveAuthToken(token: String)=viewModelScope.launch {
        repository.saveAuthToken(token)
    }



    private val _registerResponse:MutableLiveData<Resource<RegisterResponse>> =MutableLiveData()
    val registerResponse:LiveData<Resource<RegisterResponse>>
        get()=_registerResponse
    fun register(name:String,email: String,password:String,phone:String)=  viewModelScope.launch {
        _registerResponse.value=repository.register(name,email,password,phone)
    }



}