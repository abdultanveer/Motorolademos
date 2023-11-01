package com.example.motorolademos

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
var TAG = "AdditionService"
class AdditionService : Service() {
    private val binderPipe = LocalBinder()

    internal inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val receivedBundle = msg.data

            Log.i(TAG,receivedBundle.getString("clientmsg").toString());

            val message = Message.obtain(this@IncomingHandler, 0)
            val bundle = Bundle()
            bundle.putInt("sum", 77)
            message.data = bundle
            msg.replyTo.send(message)
        }
    }

    private val mMessenger = Messenger(IncomingHandler())


        private val aidlBinder = object : IAddInterface.Stub() {
        override fun add(fno: Int, sno: Int): Int {
            Log.i(TAG, "result in aidl is--"+ fno+sno)
            return fno + sno;
        }
    }
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
        return mMessenger.binder
        //aidlBinder
    }


}