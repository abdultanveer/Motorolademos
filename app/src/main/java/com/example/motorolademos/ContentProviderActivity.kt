package com.example.motorolademos

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.Contacts
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity


class ContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        val uriSms = Uri.parse("content://sms/inbox")

        val contactsUri = ContactsContract.Contacts.CONTENT_URI
        //select * from inbox
        val cursor = contentResolver.query(contactsUri,null,null,null,null)
        val values = ContentValues()
      /*  values.put(ContactsContract.Contacts.DISPLAY_NAME,"moto-malay")
        contentResolver.insert(contactsUri,values)*/

       // val fromColNames = arrayOf("address","body")
        val fromColsContacts = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
        val toViewIds = intArrayOf(android.R.id.text1)

        var cursorAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_1,cursor,fromColsContacts,toViewIds)
        var listview:ListView = findViewById(R.id.lvContentprovider)
        listview.adapter = cursorAdapter
    }
}