package com.coder.bpitstock.ui.auth.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.coder.bpitstock.R
import com.coder.bpitstock.data.model.Item
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.network.UserApi
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.databinding.AddItemFragmentBinding
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.trending.AdapterForTrend
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AddItem :BaseFragment<HomeViewModel,AddItemFragmentBinding, UserRepository>() {


    // private lateinit var viewModel: LoginViewModel
    override fun getViewModel()=HomeViewModel::class.java
    lateinit var navController: NavController
   // val token= runBlocking {  userPref.authToken.first()}
    var itemss=ArrayList<Item>()



    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=AddItemFragmentBinding.inflate(inflater,container,false)

   // override fun getFragementRepository()= UserRepository(remoteDataSource.buildApi(UserApi::class.java))





    override fun getFragementRepository(): UserRepository {
        val token= runBlocking {  userPref.authToken.first()}
        val api=remoteDataSource.buildApi(UserApi::class.java,token)
        return  UserRepository(api)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            // binding.myLoginProgress.visible(false)
        navController= Navigation.findNavController(view)
      //  viewModel.addItems(


        viewModel.additems.observe(viewLifecycleOwner, Observer {
          //  binding.myLoginProgress.visible(false)

            when(it){
                is Resource.Success->{
                    Toast.makeText(requireContext(),it.toString(), Toast.LENGTH_LONG).show()

                    val action =AddItemDirections.actionAddItemToLaunchHome()
                    navController.navigate(action)

                   // viewModel.saveAuthToken(it.value.token)
                  //  requireActivity().startNewActivity(HomeActivity::class.java)


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
//        binding.dontHaveAccount.setOnClickListener{
//            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
//            navController!!.navigate(action)
//
//        }
        binding.addItemsButton.setOnClickListener{

            viewModel.addItems(binding.billNo.text.toString().toInt(),binding.date.text.toString(),
            binding.suppname.text.toString(),binding.balance.text.toString().toInt(),itemss)
        }
        binding.addMore.setOnClickListener{
            itemss!!.add(Item(binding.itemName.text.toString(),binding.recivedQuan.text.toString()))
            binding.itemName.text.clear()
            binding.recivedQuan.text.clear()
        }


        binding.delMore.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setCancelable(true)



            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.pop_up_delete, null)
           var recy=dialoglayout.findViewById<RecyclerView>(R.id.recmydelete)
            val adap=AdapterForTrend(requireContext(),itemss,"Y")
            recy.adapter=adap


            builder.setView(dialoglayout)
            builder.show()
        }


    }

}