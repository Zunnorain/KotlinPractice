package com.example.kotlinpractice

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlinpractice.databinding.ActivityMainBinding
import com.example.kotlinpractice.services.foreground.ForegroundNotificationService
import com.example.kotlinpractice.services.normal.MusicService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Foreground Service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
            )
        }

        receiver = AirplaneModeReceiver()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //FAB OnCLick
        binding.fab.setOnClickListener {
            intent = Intent(this, TipCalculator::class.java)
            startActivity(intent)
        }

        binding.buttonStart.setOnClickListener {
            startService(Intent(this, MusicService::class.java))
        }
        binding.buttonEnd.setOnClickListener {
            stopService(Intent(this, MusicService::class.java))
        }
        binding.buttonRV.setOnClickListener {
            intent = Intent(this, RvActivity::class.java)
            startActivity(intent)
        }

        binding.foregroundBtn.setOnClickListener {
            Intent(applicationContext, ForegroundNotificationService::class.java).also {
                it.action = ForegroundNotificationService.Actions.START.toString()
                startService(it)
            }
        }

        //BroadCast Receiver
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }
}