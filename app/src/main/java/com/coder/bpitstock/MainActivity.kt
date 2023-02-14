package com.coder.bpitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.coder.bpitstock.data.UserPref
import com.coder.bpitstock.ui.auth.AuthActivity
import com.coder.bpitstock.ui.auth.home.HomeActivity
import com.coder.bpitstock.ui.auth.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref=UserPref(this)
        pref.authToken.asLiveData().observe(this, Observer {
           val activity= if (it==null) AuthActivity::class.java else HomeActivity::class.java
           startNewActivity(activity)
        })
       // startNewActivity(AuthActivity::class.java)


    }
}