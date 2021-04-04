package com.example.mobileappdevproj.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileappdevproj.Model.Products

class DBHelper_Prod(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
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
                val products = Products()
                products.prod_id = cursor.getInt(cursor.getColumnIndex(PROD_ID))
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

    fun addProd(prod: Products)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PROD_NAME, prod.prod_name)
        values.put(PROD_DESC, prod.prod_desc)
        values.put(PROD_SIZE, prod.prod_size)
        values.put(PROD_PRICE, prod.prod_price)
        values.put(PROD_IMG, prod.prod_img)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}