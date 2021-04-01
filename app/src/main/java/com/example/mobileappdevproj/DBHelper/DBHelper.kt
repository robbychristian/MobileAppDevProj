package com.example.mobileappdevproj.DBHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "QuadCore.db"
        private val TABLE_NAME = "Users"
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_EMAIL = "Email"
        private val COL_ADDRESS = "Address"
        private val COL_CONTACT = "Contact"
        private val COL_PASS = "Pass"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE "+ TABLE_NAME + "(" + COL_ID + "INTEGER PRIMARY KEY,"
                + COL_NAME + " TEXT," + COL_EMAIL + " TEXT," + COL_ADDRESS + " TEXT," + COL_CONTACT
                + " TEXT," + COL_PASS + " TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}