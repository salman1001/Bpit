package com.coder.bpitstock.ui.auth.home.delete

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.coder.bpitstock.R
import com.coder.bpitstock.data.model.Item
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.network.UserApi
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.data.responses.ItemXX
import com.coder.bpitstock.data.responses.SunDayAllitems
import com.coder.bpitstock.data.responses.SunDayAllitemsItem
import com.coder.bpitstock.databinding.FragmentDeleteItemBinding
import com.coder.bpitstock.databinding.FragmentLaunchHomeBinding
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.AddItemDirections
import com.coder.bpitstock.ui.auth.home.HomeFragmentArgs
import com.coder.bpitstock.ui.auth.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.math.sin

class DeleteItem : BaseFragment<HomeViewModel,FragmentDeleteItemBinding,UserRepository>() {

    override fun getViewModel()=HomeViewModel::class.java
    val args: DeleteItemArgs by navArgs<DeleteItemArgs>()
    var sunadayAllItems:SunDayAllitems?=null
    lateinit var navController: NavController



    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentDeleteItemBinding.inflate(inflater,container,false)

    override fun getFragementRepository(): UserRepository {
        val token= runBlocking {  userPref.authToken.first()}
        val api=remoteDataSource.buildApi(UserApi::class.java,token)
        return  UserRepository(api)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)

        sunadayAllItems=args.passsss.sunDayAllitems
        binding.gosearch.setOnClickListener{
            var found=false
            var singg=SunDayAllitemsItem(0,"0",12,12,"hf","jd", listOf<ItemXX>(ItemXX("sod","sod",0)),"JD","UR")
            var tyed=binding.searchbox.text.toString()
            for (i in 0 until  sunadayAllItems!!.size){
                Log.d(TAG, "onViewCreated: "+tyed+"  "+sunadayAllItems!![i].billNumber)
                if (tyed == sunadayAllItems!![i].billNumber.toString()){
                    Log.d(TAG, "onViewCreated: found")
                    found=true;
                    singg= sunadayAllItems!![i]

                }


            }
            if (found){
                binding.name.text=singg.items[0].itemName
                binding.billNo.text= singg.billNumber.toString()
                binding.qaun.text= singg.items[0].recievedQuantity.toString()
                binding.laayfound.visibility=View.VISIBLE

            }



            viewModel.delitems.observe(viewLifecycleOwner, Observer {
                //  binding.myLoginProgress.visible(false)

                when(it){
                    is Resource.Success->{
                        Toast.makeText(requireContext(),it.toString(), Toast.LENGTH_LONG).show()

                        val action = DeleteItemDirections.actionDeleteItemToLaunchHome()
                        navController.navigate(action)




                    }
                    is Resource.Failure->{
                        Toast.makeText(requireContext(),"Login Fail", Toast.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            })





                binding.dellItemsButton.setOnClickListener {
                    viewModel.delItem(singg._id)
                }


        }

    }
}