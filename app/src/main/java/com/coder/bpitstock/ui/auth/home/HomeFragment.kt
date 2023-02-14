package com.coder.bpitstock.ui.auth.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.network.UserApi
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.data.responses.IssuedItems
import com.coder.bpitstock.data.responses.SunDayAllitems
import com.coder.bpitstock.databinding.FragmentHomeBinding
import com.coder.bpitstock.ui.auth.base.BaseFragment
import com.coder.bpitstock.ui.auth.visible
import com.coder.bpitstock.ui.auth.xcel.Cell
import com.coder.bpitstock.ui.auth.xcel.ColumnHeader
import com.coder.bpitstock.ui.auth.xcel.MyTableViewAdapter
import com.coder.bpitstock.ui.auth.xcel.RowHeader
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding,UserRepository>() {

    private var mRowHeaderList: List<RowHeader>? = null
    private var mColumnHeaderList: List<ColumnHeader>? = null
    private var mCellList: List<List<Cell>>? = null
    val args: HomeFragmentArgs by navArgs<HomeFragmentArgs>()
    var type:String?=null





    override fun getViewModel(): Class<HomeViewModel> =HomeViewModel::class.java
    override fun getFragementBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragementRepository(): UserRepository {
        val token= runBlocking {  userPref.authToken.first()}
        val api=remoteDataSource.buildApi(UserApi::class.java,token)
        return  UserRepository(api)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRowHeaderList= listOf(RowHeader(1), RowHeader(2),RowHeader(3),RowHeader(4))
        mColumnHeaderList= listOf(ColumnHeader("morjkf"), ColumnHeader("fsdg"),
            ColumnHeader("sgsfwrv"),
            ColumnHeader("fgsdf")
        )
        mCellList= listOf(listOf(Cell("edfh"),Cell("kuhfg")), listOf(Cell("kjdfh")))


        binding.homeProg.visible(false)
        type=args.type
        if (type=="A"){
            viewModel.getItems()
            viewModel.items.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, "onViewCreated:  called 1")

                when(it){
                    is Resource.Success->{
                        binding.homeProg.visible(false)
                        Log.d(TAG, "onViewCreated:  called ")


                        updateUi(it.value)

                    }
                    is Resource.Loading->{
                        binding.homeProg.visible(true)
                    }
                    is Resource.Failure->{
                        Toast.makeText(requireContext(),"Failed", Toast.LENGTH_LONG).show()

                    }
                    else -> {}
                }
            })

        }
        else{
            viewModel.getissuedItems()
            viewModel.issueditems.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, "onViewCreated:  called 1")

                when(it){
                    is Resource.Success->{
                        binding.homeProg.visible(false)
                        Log.d(TAG, "onViewCreated:  called ")


                        updateUi1(it.value)

                    }
                    is Resource.Loading->{
                        binding.homeProg.visible(true)
                    }
                    is Resource.Failure->{
                        Toast.makeText(requireContext(),"Failed", Toast.LENGTH_LONG).show()

                    }
                    else -> {}
                }
            })

        }




    }

    private fun updateUi1(value: IssuedItems) {

        val adapter = MyTableViewAdapter()
        binding.table.setAdapter(adapter)
        //  binding.table.setBackgroundColor(resources.getColor(R.color.purple_200))




//        val __v: Int,
//        val _id: String,
//        val balance: Int,
//        val billNumber: Int,
//        val date: String,
//        val issuedQuantity: Int,
//        val itemName: String,
//        val recievedQuantity: Int,
//        val supplierName: String



        mColumnHeaderList= listOf(ColumnHeader("Date"),
            ColumnHeader("Item Name"),
            ColumnHeader("Department"),
            ColumnHeader("Issued Quantity")



        )

        var listt=ArrayList<ArrayList<Cell>>()

        /// for(k in 1..2){
        for (i in 0 until value.size){
            listt.add(ArrayList())
            var cur=value[i]
            listt.get(i).add(Cell(cur.date.substring(0,10).toString()))
            listt.get(i).add(Cell(cur.itemName))
            listt.get(i).add(Cell(cur.department))
            listt.get(i).add(Cell(cur.issuedQuantity.toString()))

            //   listt.get(i).add(Cell(cur.items[0].toString()))
            //listt.get(i).add(Cell(cur.recievedQuantity.toString()))
          //  listt.get(i).add(Cell(cur.supplierName.toString()))
            // listt.get(i).add(Cell(cur.issuedQuantity.toString()))
          //  listt.get(i).add(Cell(cur.balance.toString()))


        }
        //   }


        mCellList= listt
//        mRowHeaderList= listOf(
////            RowHeader("1"), RowHeader("2"),RowHeader("3"),RowHeader("4")
//        )
        var list=ArrayList<RowHeader>()
        for (i in 0 until value.size){
            list.add(RowHeader((i+1).toString()))
        }

        mRowHeaderList=list


        adapter.setAllItems(mColumnHeaderList,mRowHeaderList,mCellList)




    }

    private fun updateUi(newAllAddedItems: SunDayAllitems) {
        val adapter = MyTableViewAdapter()
        binding.table.setAdapter(adapter)
      //  binding.table.setBackgroundColor(resources.getColor(R.color.purple_200))




//        val __v: Int,
//        val _id: String,
//        val balance: Int,
//        val billNumber: Int,
//        val date: String,
//        val issuedQuantity: Int,
//        val itemName: String,
//        val recievedQuantity: Int,
//        val supplierName: String



          mColumnHeaderList= listOf(ColumnHeader("Bill Number"),
              ColumnHeader("Date"),
             ColumnHeader("Supplier Name"),
              ColumnHeader("Balance"),
              ColumnHeader("Item Name"),
              ColumnHeader("Received Quantity"),



         )

        var listt=ArrayList<ArrayList<Cell>>()

       /// for(k in 1..2){
            for (i in 0 until newAllAddedItems.size){
                listt.add(ArrayList())
                var cur=newAllAddedItems[i]
                listt.get(i).add(Cell(cur.billNumber.toString()))
                listt.get(i).add(Cell(cur.date.substring(0,10).toString()))
               // listt.get(i).add(Cell(cur.items[0].toString()))
                //listt.get(i).add(Cell(cur.recievedQuantity.toString()))
                listt.get(i).add(Cell(cur.supplierName.toString()))
               // listt.get(i).add(Cell(cur.issuedQuantity.toString()))
                listt.get(i).add(Cell(cur.balance.toString()))
//                if (cur.items.isNotEmpty() ){
//                    listt.get(i).add(Cell(cur.items[0].itemName))
//                    listt.get(i).add(Cell(cur.items[0].recievedQuantity))
//
//                }
//                else{
//                    listt.get(i).add(Cell("NULL"))
//                    listt.get(i).add(Cell("NULL"))
//                }




            }
     //   }


          mCellList= listt
//        mRowHeaderList= listOf(
////            RowHeader("1"), RowHeader("2"),RowHeader("3"),RowHeader("4")
//        )
        var list=ArrayList<RowHeader>()
        for (i in 0 until newAllAddedItems.size){
            list.add(RowHeader((i+1).toString()))
        }

        mRowHeaderList=list


        adapter.setAllItems(mColumnHeaderList,mRowHeaderList,mCellList)

    }


}