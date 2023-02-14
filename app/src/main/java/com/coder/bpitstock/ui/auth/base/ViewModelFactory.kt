package com.coder.bpitstock.ui.auth.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coder.bpitstock.data.repository.AuthRepository
import com.coder.bpitstock.data.repository.BaseRepository
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.ui.auth.home.HomeViewModel

class ViewModelFactory(private  val repository: BaseRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return  when {
            modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(repository as UserRepository) as T

            else -> throw IllegalAccessException("ViewModel Class Not Found")
        }

    }

}