package com.coder.bpitstock.ui.auth

import android.app.Activity
import android.content.Intent
import android.text.BoringLayout
import android.view.View

fun<A :Activity> Activity.startNewActivity(activity:Class<A>){
    Intent(this,activity).also {
        it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

}


fun View.visible(isVisible:Boolean){
    visibility=if (isVisible) View.VISIBLE else View.GONE
}
fun View.enable(enabled:Boolean){
    isEnabled=enabled

}