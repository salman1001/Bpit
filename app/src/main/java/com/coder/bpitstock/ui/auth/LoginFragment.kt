package com.coder.bpitstock.ui.auth

import android.content.ContentValues
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.coder.bpitstock.R
import com.coder.bpitstock.databinding.FragmentLoginBinding
import com.coder.bpitstock.data.network.AuthApi
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.repository.AuthRepository
import com.coder.bpitstock.ui.auth.base.AuthViewModel
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.HomeActivity
import kotlinx.coroutines.launch

class LoginFragment :BaseFragment<AuthViewModel,FragmentLoginBinding, AuthRepository>() {


   // private lateinit var viewModel: LoginViewModel
    override fun getViewModel()=AuthViewModel::class.java
    lateinit var navController: NavController

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {0
//                activity!!.onBackPressed()
//
//
//
//
//
//            }
//        })
//
//    }









    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragementRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPref)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myLoginProgress.visible(false)
        navController= Navigation.findNavController(view)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.myLoginProgress.visible(false)

            when(it){
                is Resource.Success->{

                        viewModel.saveAuthToken(it.value.token)
                        requireActivity().startNewActivity(HomeActivity::class.java)


                }
                is Resource.Failure->{
                    Toast.makeText(requireContext(),"Login Fail",Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })
        binding.loginLoginbtn.setOnClickListener{
            val email=binding.loginEmail.text.toString()
            val password=binding.LoginPass.text.toString()
            binding.myLoginProgress.visible(true)

            viewModel.login(email,password)

        }
        binding.dontHaveAccount.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController!!.navigate(action)

        }

    }

}