package com.example.motorolademos

import android.content.ComponentName
import android.content.ContentValues
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.motorolademos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mService : AdditionService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startAdditionService()
        }
        binding.btnInsert.setOnClickListener { insertContentProvider() }

        binding.btnStop.setOnClickListener { stopAddService() }

        binding.btnBind.setOnClickListener { bindAddService() }

        binding.btnUnbind.setOnClickListener { unbindAddService() }


    }

    private fun insertContentProvider() {
        val usersUri = Uri.parse("content://com.moto.usersdb")
        val values = ContentValues()
        values.put("name",binding.etName.text.toString())
        contentResolver.insert(usersUri,values)

    }

    private fun unbindAddService() {
        unbindService(connection)
    }

    private fun bindAddService() {
        val serviceIntent = Intent(this,AdditionService::class.java)
        bindService(serviceIntent,connection, BIND_AUTO_CREATE)

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

    private val connection = object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, binderPipe: IBinder?) {
            val binder = binderPipe as AdditionService.LocalBinder
            mService = binder.getAddService()
            val sum = mService.add(10,20)
            binding.tvResult.text = sum.toString()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }
}