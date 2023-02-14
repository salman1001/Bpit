package com.coder.bpitstock.ui.auth.home.issueitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.network.UserApi
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.databinding.FragmentIssueItemsBinding
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class IssueItems : BaseFragment<HomeViewModel, FragmentIssueItemsBinding, UserRepository>() {

    override fun getViewModel(): Class<HomeViewModel> =HomeViewModel::class.java
    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentIssueItemsBinding.inflate(inflater,container,false)

    override fun getFragementRepository(): UserRepository {
        val token= runBlocking {  userPref.authToken.first()}
        val api=remoteDataSource.buildApi(UserApi::class.java,token)
        return  UserRepository(api)
    }
    lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)







        viewModel.issueitems.observe(viewLifecycleOwner, Observer {
            //  binding.myLoginProgress.visible(false)

            when(it){
                is Resource.Success->{
                    Toast.makeText(requireContext(),it.toString(), Toast.LENGTH_LONG).show()

                    val action =IssueItemsDirections.actionIssueItemsToLaunchHome()
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
        binding.issueItemsButton.setOnClickListener {
            viewModel.issueItem(binding.name.text.toString(),binding.date.text.toString()
            ,binding.department.text.toString(),binding.qunatity.text.toString().toInt())
        }

    }


}