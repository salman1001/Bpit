package com.coder.bpitstock.ui.auth.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coder.bpitstock.data.network.Resource
import com.coder.bpitstock.data.repository.UserRepository
import com.coder.bpitstock.data.responses.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository):ViewModel() {
    private val _item:MutableLiveData<Resource<SunDayAllitems>> = MutableLiveData()
    val items:LiveData<Resource<SunDayAllitems>>
    get()=_item
    fun getItems()=viewModelScope.launch {
        _item.value=Resource.Loading
        _item.value=repository.getItems()
    }




    private val _issueditem:MutableLiveData<Resource<IssuedItems>> = MutableLiveData()
    val issueditems:LiveData<Resource<IssuedItems>>
        get()=_issueditem
    fun getissuedItems()=viewModelScope.launch {
        _issueditem.value=Resource.Loading
        _issueditem.value=repository.getIssuedItems()
    }




    private val _addItem:MutableLiveData<Resource<NewAddItemRespose>> = MutableLiveData()
    val additems:LiveData<Resource<NewAddItemRespose>>
        get()=_addItem
    fun addItems(
        billNumber: Int, date: String,  supplierName: String, balance: Int,items:List<com.coder.bpitstock.data.model.Item>
    )=viewModelScope.launch {
        _addItem.value=Resource.Loading
        _addItem.value=repository.addItems(billNumber,date,supplierName,balance,items)
    }






      private val _issueItem:MutableLiveData<Resource<IssueSingleItemResponse>> = MutableLiveData()
    val issueitems:LiveData<Resource<IssueSingleItemResponse>>
        get()=_issueItem
    fun issueItem(
        name:String, date: String,  department: String, quantity: Int,
    )=viewModelScope.launch {
        _issueItem.value=Resource.Loading
        _issueItem.value=repository.issueItem(name,date,department,quantity)
    }



    private val _delItem:MutableLiveData<Resource<DeleteResponse>> = MutableLiveData()
    val delitems:LiveData<Resource<DeleteResponse>>
        get()=_delItem
    fun delItem(
id:String    )=viewModelScope.launch {
        _delItem.value=Resource.Loading
        _delItem.value=repository.delete(id)
    }




}