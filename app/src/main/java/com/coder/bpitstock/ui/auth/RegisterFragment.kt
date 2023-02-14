package com.coder.bpitstock.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.coder.bpitstock.R
import com.coder.bpitstock.data.network.AuthApi
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.repository.AuthRepository
import com.coder.bpitstock.databinding.FragmentLoginBinding
import com.coder.bpitstock.databinding.FragmentRegisterBinding
import com.coder.bpitstock.ui.auth.base.AuthViewModel
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.HomeActivity
import com.google.android.material.navigation.NavigationView

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>()  {



    // private lateinit var viewModel: LoginViewModel
    override fun getViewModel()= AuthViewModel::class.java
    lateinit var navController:NavController

    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater,container,false)

    override fun getFragementRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPref)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  binding.myLoginProgress.visible(false)
        navController=Navigation.findNavController(view)


        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
           // binding.myLoginProgress.visible(false)

            when(it){
                is Resource.Success->{
                    Toast.makeText(requireContext(),it.value._id, Toast.LENGTH_LONG).show()
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    navController!!.navigate(action)

                 //   viewModel.saveAuthToken(it.value.token)

                    //requireActivity().startNewActivity(HomeActivity::class.java)


                }
                is Resource.Failure->{
                    Toast.makeText(requireContext(),"Login Fail", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })
//        binding.loginLoginbtn.setOnClickListener{
//            val email=binding.loginEmail.text.toString()
//            val password=binding.LoginPass.text.toString()
//            binding.myLoginProgress.visible(true)
//
//            viewModel.login(email,password)
//
//        }
        binding.signUpRegister.setOnClickListener{
            val email=binding.signUpEmail.text.toString()
           val password=binding.sighUpConfirm.text.toString()
            val phone=binding.sighUpPhone.text.toString()
            val name=binding.sighUpName.text.toString()
            viewModel.register(name,email,password,phone)

        }

    }

}