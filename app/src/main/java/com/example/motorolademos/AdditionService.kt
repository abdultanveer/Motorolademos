package com.example.motorolademos

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
var TAG = "AdditionService"
class AdditionService : Service() {
    private val binderPipe = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getAddService():AdditionService{
            return this@AdditionService
        }
    }


        override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service created")

    }

    fun add(fno:Int, sno: Int):Int{
        return  fno + sno;
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
        return binderPipe
    }


}