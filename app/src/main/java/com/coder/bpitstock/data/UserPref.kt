package com.coder.bpitstock.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref(
    context: Context
) {
    private val applicationContext=context.applicationContext
    private val datastore: DataStore<Preferences> = applicationContext.createDataStore(name="my_data_store")

    val authToken:Flow<String?>
    get()=datastore.data.map { pref->pref[KEY_AUTH] }
    suspend fun saveToken(authToken:String){
        datastore.edit {
            pref->pref[KEY_AUTH]=authToken

        }

    }
    companion object{
        private val KEY_AUTH= preferencesKey<String>("key_auth")
    }
}