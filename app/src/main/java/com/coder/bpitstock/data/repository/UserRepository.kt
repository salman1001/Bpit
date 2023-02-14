package com.coder.bpitstock.data.repository

import android.support.v4.media.session.MediaSessionCompat.Token
import com.coder.bpitstock.data.UserPref
import com.coder.bpitstock.data.model.*
import com.coder.bpitstock.data.network.AuthApi
import com.coder.bpitstock.data.network.UserApi
import com.google.gson.annotations.SerializedName

class UserRepository(private val api: UserApi): BaseRepository(){
   suspend fun getItems()=safeApiCall { api.getItems()}

    suspend fun getIssuedItems()=safeApiCall { api.getIssuedItems()}





    suspend fun addItems(

       billNumber: Int, date: String, supplierName: String, balance: Int,items:List<Item>


   )=safeApiCall {
       var addnewItem=NewAddtems(balance.toString(),billNumber.toString(),date,items,supplierName)
       api.addItem(addnewItem)}





suspend fun issueItem(

    itmename: String, date: String, department: String, qauntity: Int


)=safeApiCall {
    var issue=IssueItem(itmename,date,department,qauntity)
    api.issue(issue)}

    suspend fun delete(
        id:String
    )=safeApiCall {
        var del=DeleteItem(id)
        api.delete(del)}


}



