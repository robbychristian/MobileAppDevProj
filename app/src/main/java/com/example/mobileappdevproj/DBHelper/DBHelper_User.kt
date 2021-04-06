package com.example.mobileappdevproj.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileappdevproj.Model.User

class DBHelper_User(context: Context?):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
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
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $COL_NAME TEXT, $COL_EMAIL TEXT, $COL_ADDRESS TEXT, $COL_CONTACT TEXT, $COL_PASS TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allPerson:ArrayList<User>
    get() {
        val listUsers = ArrayList<User>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst())
        {
            do {
                val user = User()
                user.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                user.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                user.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                user.address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS))
                user.num = cursor.getString(cursor.getColumnIndex(COL_CONTACT))
                user.pass = cursor.getString(cursor.getColumnIndex(COL_PASS))

                listUsers.add(user)
            } while (cursor.moveToNext())
        }
        db.close()
        return listUsers
    }

    fun addUser(user: User)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, user.name)
        values.put(COL_EMAIL, user.email)
        values.put(COL_ADDRESS, user.address)
        values.put(COL_CONTACT, user.num)
        values.put(COL_PASS, user.pass)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateUser(user: User):Int
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, user.name)
        values.put(COL_EMAIL, user.email)
        values.put(COL_ADDRESS, user.address)
        values.put(COL_CONTACT, user.num)
        values.put(COL_PASS, user.pass)

        return db.update(TABLE_NAME, values, "$COL_EMAIL=?", arrayOf(user.email.toString()))
    }
    //LOGIN
    fun loginUser(email:String, pass:String): Boolean {
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_EMAIL=? AND $COL_PASS=?"
        val whereArgs = arrayOf(email, pass)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, whereArgs)

        return cursor.count == 1
    }
    //REGISTER FUNCTIONS
    fun emailExists(email: String): Boolean {
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_EMAIL=?"
        val whereArgs = arrayOf(email)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, whereArgs)
        val count = cursor.count

        cursor.close()
        return count >= 1
    }

}