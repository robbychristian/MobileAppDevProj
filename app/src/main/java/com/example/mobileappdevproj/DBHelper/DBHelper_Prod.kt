package com.example.mobileappdevproj.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileappdevproj.Model.Products

class DBHelper_Prod(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "QuadCore.db"
        private val TABLE_NAME = "Products"
        private val PROD_ID = "prod_id"
        private val PROD_NAME = "prod_name"
        private val PROD_DESC = "prod_desc"
        private val PROD_SIZE = "prod_size"
        private val PROD_PRICE = "prod_price"
        private val PROD_IMG = "prod_img"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($PROD_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PROD_NAME TEXT, $PROD_DESC TEXT, $PROD_SIZE TEXT, " +
                "$PROD_PRICE TEXT, $PROD_IMG TEXT")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allProd:List<Products>
    get() {
        val listProd = ArrayList<Products>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst())
        {
            do {
                val products = Products(PROD_NAME, PROD_DESC, PROD_SIZE, PROD_PRICE, PROD_IMG)
                products.prod_name = cursor.getString(cursor.getColumnIndex(PROD_NAME))
                products.prod_desc = cursor.getString(cursor.getColumnIndex(PROD_DESC))
                products.prod_size = cursor.getString(cursor.getColumnIndex(PROD_SIZE))
                products.prod_price = cursor.getString(cursor.getColumnIndex(PROD_PRICE))
                products.prod_img = cursor.getString(cursor.getColumnIndex(PROD_IMG))
            } while (cursor.moveToNext())
        }
        db.close()
        return listProd
    }

    fun prodList(): ArrayList<Products> {
        val prodList: ArrayList<Products> = ArrayList<Products>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        var cursor: Cursor? = null
        cursor = db.rawQuery(selectQuery,null)


        if (cursor.moveToFirst()) {
            do {
                var name = cursor.getString(cursor.getColumnIndex(PROD_NAME))
                var desc = cursor.getString(cursor.getColumnIndex(PROD_DESC))
                var price = cursor.getString(cursor.getColumnIndex(PROD_PRICE))
                var size = cursor.getString(cursor.getColumnIndex(PROD_SIZE))
                var img = cursor.getString(cursor.getColumnIndex(PROD_IMG))
            } while (cursor.moveToNext())
        }
        return prodList
    }
}