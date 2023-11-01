package com.example.motorolademos

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
var TAG = "AdditionService"
class AdditionService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service created")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Log.i(TAG, "downloading from--"+intent?.extras?.getString("downloadurl"))
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"service destroyed")

    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}