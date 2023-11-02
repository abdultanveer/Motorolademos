package com.example.motorolademos

import android.net.Uri
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity


class ContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        val uriSms = Uri.parse("content://sms/inbox")
        //select * from inbox
        val cursor = contentResolver.query(uriSms,null,null,null,null)

        val fromColNames = arrayOf("address","body")
        val toViewIds = intArrayOf(android.R.id.text1,android.R.id.text2)

        var cursorAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,cursor,fromColNames,toViewIds)
        var listview:ListView = findViewById(R.id.lvContentprovider)
        listview.adapter = cursorAdapter

    }
}