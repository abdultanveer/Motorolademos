package com.example.motorolademos.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class UsersContentProvider : ContentProvider() {
    val PROVIDER_NAME = "com.moto.usersdb"
    val URL =
        "content://" + PROVIDER_NAME + "/users"
    val CONTENT_URI = Uri.parse(URL)


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowId = db?.insert(TABLE_NAME,"",values)
        //val _uri = ContentUris.withAppendedId(CONTENT_URI,rowId!!)
        return  CONTENT_URI
    }

    override fun onCreate(): Boolean {
        var dbHelper = DatabaseHelper(context!!)
        db = dbHelper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor = db?.query(TABLE_NAME,null,null,null,null,null,null)
        return  cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

    private var db: SQLiteDatabase? = null
companion object {
    // declaring name of the database
    val DATABASE_NAME = "UserDB"

    // declaring table name of the database
    val TABLE_NAME = "Users"

    // declaring version of the database
    val DATABASE_VERSION = 1

    // sql query to create the table
    val CREATE_DB_TABLE = (" CREATE TABLE " + TABLE_NAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " name TEXT NOT NULL);")
}

    class DatabaseHelper(var context: Context)
        : SQLiteOpenHelper(context, DATABASE_NAME,null,
        DATABASE_VERSION){

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

            // sql query to drop a table
            // having similar name
           /* db.execSQL("DROP TABLE IF EXISTS " + com.example.customcontentprovider.MyContentProvider.TABLE_NAME)
            onCreate(db)    */
        }

    }

}