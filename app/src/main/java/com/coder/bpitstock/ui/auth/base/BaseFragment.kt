package com.coder.bpitstock.ui.auth.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.coder.bpitstock.data.UserPref
import com.coder.bpitstock.data.network.RemoteDataSource
import com.coder.bpitstock.data.repository.BaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {


    protected lateinit var binding:B
    protected val remoteDataSource= RemoteDataSource()
    protected lateinit var viewModel:VM
    protected lateinit var userPref: UserPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPref=UserPref(requireContext())
        binding=getFragementBinding(inflater,container)
        val factory=ViewModelFactory(getFragementRepository())
        viewModel=ViewModelProvider(this,factory).get(getViewModel())
       lifecycleScope.launch{
           userPref.authToken.first() }


        return binding.root
    }
    abstract fun getViewModel():Class<VM>

    abstract fun getFragementBinding(inflater: LayoutInflater,container: ViewGroup?):B

    abstract fun getFragementRepository():R



}