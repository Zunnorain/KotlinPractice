package com.example.kotlinpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state",false) ?: return
        if(isAirplaneModeEnabled){
            Toast.makeText(context, "Airplane mode Enabled", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Airplane mode disabled", Toast.LENGTH_SHORT).show()
        }
    }

}