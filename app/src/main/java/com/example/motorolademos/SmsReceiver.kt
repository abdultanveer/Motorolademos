package com.example.motorolademos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log


class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Sms","you received an sms")

        val bundle = intent.extras
        if (bundle != null) {
            val pdus = bundle["pdus"] as Array<Any>?
            val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus!!.size)
            for (i in pdus!!.indices) {
                messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
            }
            if (messages.size > -1) {
                Log.i("Sms", "Message recieved: " + (messages[0]?.getMessageBody() ))
            }

        }
    }
}