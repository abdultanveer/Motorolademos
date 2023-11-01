package com.example.motorolademos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motorolademos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startAdditionService()
        }

        binding.btnStop.setOnClickListener { stopAddService() }


    }

    private fun stopAddService() {
        val serviceIntent = Intent(this,AdditionService::class.java)
        stopService(serviceIntent)
    }

    private fun startAdditionService() {
        val serviceIntent = Intent(this,AdditionService::class.java)
        serviceIntent.putExtra("downloadurl","https://downloadimage.com")
        startService(serviceIntent)
    }
}