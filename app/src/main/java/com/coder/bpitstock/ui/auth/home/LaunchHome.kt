package com.coder.bpitstock.ui.auth.home

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.coder.bpitstock.R
import com.coder.bpitstock.data.model.Item
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.network.UserApi
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.data.responses.SunDayAllitems
import com.coder.bpitstock.databinding.AddItemFragmentBinding
import com.coder.bpitstock.databinding.FragmentLaunchHomeBinding
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.home.random.PassItOn
import com.coder.bpitstock.ui.auth.home.random.Sort
import com.coder.bpitstock.ui.auth.home.trending.AdapterForTrend
import com.coder.bpitstock.ui.auth.home.trending.SingleItem
import com.coder.bpitstock.ui.auth.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class LaunchHome : BaseFragment<HomeViewModel, FragmentLaunchHomeBinding, UserRepository>() {
    lateinit var navController:NavController
    lateinit var sunDayAllitems: SunDayAllitems

     var trending:AdapterForTrend?=null

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
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
//


//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_launch_home, container, false)
//    }

    override fun getViewModel()=HomeViewModel::class.java

    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=FragmentLaunchHomeBinding.inflate(inflater,container,false)

    override fun getFragementRepository(): UserRepository {
        val token= runBlocking {  userPref.authToken.first()}
        val api=remoteDataSource.buildApi(UserApi::class.java,token)
        return  UserRepository(api)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)

        binding.issueSingle.setOnClickListener{
            val action=LaunchHomeDirections.actionLaunchHomeToIssueItems()
            navController.navigate(action)
        }



       binding.Showadded.setOnClickListener{
            val action=LaunchHomeDirections.actionLaunchHomeToHomeFragment("A")
            navController.navigate(action)

//            val action=LaunchHomeDirections.actionLaunchHomeToAddItem()
//            navController.navigate(action)
        }
        binding.Showissued.setOnClickListener {
            val action=LaunchHomeDirections.actionLaunchHomeToHomeFragment("B")
            navController.navigate(action)
        }
        binding.layCor.setOnClickListener{
            val action=LaunchHomeDirections.actionLaunchHomeToAddItem()
           navController.navigate(action)
        }



        viewModel.getItems()
        viewModel.items.observe(viewLifecycleOwner, Observer {
            Log.d(ContentValues.TAG, "onViewCreated:  called 1")

            when(it){
                is Resource.Success->{
                   // binding.homeProg.visible(false)
                    Log.d(ContentValues.TAG, "onViewCreated:  called ")
                    sunDayAllitems=it.value
                    var s=ArrayList<Item>()
                   // var soreted=sunDayAllitems.sortWith(compareBy()


                    for (i in 0 until sunDayAllitems.size){
                        for (j in 0 until sunDayAllitems[i].items.size){
                            s.add(Item(sunDayAllitems[i].items[j].itemName,sunDayAllitems[i].items[j].recievedQuantity.toString()))
                        }
                    }



                    trending= AdapterForTrend(requireContext(), s,"N")
                    //binding.rankingOrder.adapter=trending
                    //  val rec=view.findViewById<RecyclerView>(R.id.ranking_order)
                    //  rec.adapter=trending
                    binding.rankingOrder.adapter=trending





                    // updateUi(it.value)

                }
                is Resource.Loading->{
                    //binding.homeProg.visible(true)
                }
                is Resource.Failure->{
                    Toast.makeText(requireContext(),"Failed", Toast.LENGTH_LONG).show()

                }
                else -> {}
            }
        })


























        binding.deleteee.setOnClickListener{

            val action=LaunchHomeDirections.actionLaunchHomeToDeleteItem(PassItOn(sunDayAllitems))
            navController.navigate(action)

        }
        binding.gosearch.setOnClickListener {
            val str=binding.searchbox.text.toString()
            val regex=Regex(str)
            var bb= ArrayList<Item>()

            for (i in 0 until sunDayAllitems.size){
                while (true){
                    var sunDayAllitemsItem= sunDayAllitems[i]
                    if (regex.containsMatchIn(sunDayAllitemsItem.supplierName)){
                        bb.add(Item(sunDayAllitemsItem.billNumber.toString()
                            ,sunDayAllitemsItem.items[0].itemName
                            )
                        )
                        break;
                    }

                    if (regex.containsMatchIn(sunDayAllitemsItem.createdAt)){
                        bb.add(Item(sunDayAllitemsItem.billNumber.toString()
                            ,sunDayAllitemsItem.items[0].itemName
                            ))
                        break;
                    }


                    if (regex.containsMatchIn(sunDayAllitemsItem.updatedAt)){
                        bb.add(Item(sunDayAllitemsItem.billNumber.toString()
                            ,sunDayAllitemsItem.items[0].itemName
                            ))
                        break;
                    }


                    if (regex.containsMatchIn(sunDayAllitemsItem.billNumber.toString())){
                        bb.add(Item(sunDayAllitemsItem.billNumber.toString()
                            ,sunDayAllitemsItem.items[0].itemName
                            ))
                        break;
                    }


                    for (k in 0 until sunDayAllitemsItem.items.size){
                        if (regex.containsMatchIn(sunDayAllitemsItem.items[k].itemName)){
                            bb.add(Item(sunDayAllitemsItem.billNumber.toString()
                                ,sunDayAllitemsItem.items[k].itemName
                               ))

                        }
                    }

                    break

                }


            }
            //binding.rankingOrder.
            binding.changeText.text="Your Results"


            trending= AdapterForTrend(requireContext(), bb,"N")
            //binding.rankingOrder.adapter=trending
            //  val rec=view.findViewById<RecyclerView>(R.id.ranking_order)
            //  rec.adapter=trending
            binding.rankingOrder.adapter=trending
           // binding.rankingOrder.change





        }








    }



}