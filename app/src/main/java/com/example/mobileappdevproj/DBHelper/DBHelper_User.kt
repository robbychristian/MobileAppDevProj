package com.example.mobileappdevproj.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileappdevproj.Model.Order
import com.example.mobileappdevproj.Model.Products
import com.example.mobileappdevproj.Model.User

class DBHelper_User(context: Context?):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "QuadCore.db"

        private val USER_TABLE_NAME = "Users"
        private val USER_ID = "user_id"
        private val USER_NAME = "user_name"
        private val USER_EMAIL = "user_email"
        private val USER_ADDRESS = "user_address"
        private val USER_CONTACT = "user_contact"
        private val USER_PASS = "user_pass"

        private val PROD_TABLE_NAME = "Products"
        private val PROD_ID = "prod_id"
        private val PROD_NAME = "prod_name"
        private val PROD_DESC = "prod_desc"
        private val PROD_SIZE = "prod_size"
        private val PROD_PRICE = "prod_price"
        private val PROD_IMG = "prod_img"

        private val ORDERS_TABLE_NAME = "Orders"
        private val ORDER_ID = "order_id"
        private val ORDER_NAME = "order_name"
        private val ORDER_QTY = "order_qty"
        private val ORDER_PRICE = "order_price"

        val CREATE_USERS_QUERY = ("CREATE TABLE $USER_TABLE_NAME ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_NAME TEXT, $USER_EMAIL TEXT, $USER_ADDRESS TEXT, $USER_CONTACT TEXT, $USER_PASS TEXT)")
        val CREATE_PRODUCTS_QUERY = ("CREATE TABLE $PROD_TABLE_NAME ($PROD_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PROD_NAME TEXT, $PROD_DESC TEXT, $PROD_SIZE TEXT, $PROD_PRICE TEXT, $PROD_IMG TEXT)")
        val CREATE_ORDERS_QUERY = ("CREATE TABLE $ORDERS_TABLE_NAME ($ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ORDER_NAME TEXT, $ORDER_QTY TEXT, $ORDER_PRICE TEXT)")

        val prod1 = Products(
                "Chocolate Crispy",
                "Get 5 delights from 5 layers of delicious milk and vanilla ice cream with crispy chocolate wrap",
                "60g",
                "P19",
                "2131165293"
        )
        val prod2 = Products(
                "Avocado & Strawberry",
                "A special fruits combination with chocolate syrup and strawberry sauce brings you a special experiment.",
                "800mL",
                "P120",
                "2131165281"
        )
        val prod3 = Products(
                "Chocolate Cup",
                "A simple and pure chocolate ice cream.",
                "50g",
                "P26",
                "2131165294"
        )
        val prod4 = Products(
                "Chocolate Mochi",
                "THE BEST DESSERT! Enjoy the soft sensation of sticky mochi with luscious chocolate/vanilla ice cream",
                "45mL",
                "P12",
                "2131165295"

        )
        val prod5 = Products(
                "Chocolate Stick",
                "Enjoy the creamy melting sensation of milk with delicious chocolate dressing",
                "40g",
                "P10",
                "2131165296"

        )
        val prod6 = Products(
                "Chocolate Sundae",
                "Creamy choco flavored ice cream with chocolate syrup makes it one of the people’s favorite",
                "100mL",
                "P25",
                "2131165297"
        )
        val prod7 = Products(
                "Coffee Crispy",
                "The flavor and aroma of coffee wrapped in a layer of chocolate and a sprinkling of cruncky nuts",
                "45g",
                "P16",
                "2131165299"
        )
        val prod8 = Products(
                "Mango Slush",
                "Enjoy the fresh and sweetness of natural mangoes in every bite",
                "65g",
                "P20",
                "2131165366"
        )
        val prod9 = Products(
                "Milk Melon",
                "A taste of the smoothness from sweet milk ice cream blends with fresh natural melon",
                "50g",
                "P12",
                "2131165377"
        )
        val prod10 = Products(
                "Milk Stick",
                "Enjoy all the goodness of milk in a unique and refreshing way",
                "40g",
                "P10",
                "2131165378"
        )
        val prod11= Products(
                "Pineapple Ice Stick",
                "A fresh sensation of pineapple in a way of unique tasty ice cream",
                "65g",
                "P10",
                "2131165402"
        )
        val prod12 = Products(
                "Strawberry Crispy",
                "Feel the softness of milk ice cream with sweet strawberry jam and crispy outer layer",
                "55g",
                "P19",
                "2131165404"
        )
        val prod13 = Products(
                "Sweet Corn",
                "The sweet aroma and the taste of corn blends in a unique waffle ice cream",
                "52g",
                "P15",
                "2131165405"
        )
        val prod14 = Products(
                "Vanilla Mochi",
                "THE BEST DESSERT! Enjoy the soft sensation of sticky mochi with luscious chocolate/vanilla ice cream",
                "45mL",
                "P12",
                "2131165379"
        )
        val prod15 = Products(
                "Watermelon Ice Stick",
                "The freshness and smoothness of natural watermelon now comes in a form of delicious ice cream stick",
                "65g",
                "P10",
                "2131165409"
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_USERS_QUERY)
        db!!.execSQL(CREATE_ORDERS_QUERY)
        db!!.execSQL(CREATE_PRODUCTS_QUERY)
        addProd(prod1, db)
        addProd(prod2, db)
        addProd(prod3, db)
        addProd(prod4, db)
        addProd(prod5, db)
        addProd(prod6, db)
        addProd(prod7, db)
        addProd(prod8, db)
        addProd(prod9, db)
        addProd(prod10, db)
        addProd(prod11, db)
        addProd(prod12, db)
        addProd(prod13, db)
        addProd(prod14, db)
        addProd(prod15, db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $PROD_TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $ORDERS_TABLE_NAME")
        onCreate(db!!)
    }

    val allPerson:ArrayList<User>
    get() {
        val listUsers = ArrayList<User>()
        val selectQuery = "SELECT * FROM $USER_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst())
        {
            do {
                val user = User()
                user.user_id = cursor.getInt(cursor.getColumnIndex(USER_ID))
                user.user_name = cursor.getString(cursor.getColumnIndex(USER_NAME))
                user.user_email = cursor.getString(cursor.getColumnIndex(USER_EMAIL))
                user.user_address = cursor.getString(cursor.getColumnIndex(USER_ADDRESS))
                user.user_num = cursor.getString(cursor.getColumnIndex(USER_CONTACT))
                user.user_pass = cursor.getString(cursor.getColumnIndex(USER_PASS))

                listUsers.add(user)
            } while (cursor.moveToNext())
        }
        return listUsers
    }

    fun getPerson(email: String): String {
        val person: ArrayList<User> = ArrayList()
        val selectQuery = "SELECT * FROM $USER_TABLE_NAME WHERE user_email = ?"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, arrayOf(email))
        if (cursor.moveToFirst()) {
                val user = User()
                user.user_id = cursor.getInt(cursor.getColumnIndex(USER_ID))
                user.user_name = cursor.getString(cursor.getColumnIndex(USER_NAME))
                user.user_email = cursor.getString(cursor.getColumnIndex(USER_EMAIL))
                user.user_address = cursor.getString(cursor.getColumnIndex(USER_ADDRESS))
                user.user_num = cursor.getString(cursor.getColumnIndex(USER_CONTACT))
                user.user_pass = cursor.getString(cursor.getColumnIndex(USER_PASS))
                person.add(user)
                return user.user_email.toString()
        } else {
            return "No user"
        }
    }

    fun addUser(user: User)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(USER_NAME, user.user_name)
        values.put(USER_EMAIL, user.user_email)
        values.put(USER_ADDRESS, user.user_address)
        values.put(USER_CONTACT, user.user_num)
        values.put(USER_PASS, user.user_pass)

        db.insert(USER_TABLE_NAME, null, values)
        db.close()
    }

    fun updateUser(user: User):Int
    {
        val values = ContentValues()
        values.put(USER_NAME, user.user_name)
        values.put(USER_EMAIL, user.user_email)
        values.put(USER_ADDRESS, user.user_address)
        values.put(USER_CONTACT, user.user_num)
        values.put(USER_PASS, user.user_pass)

        return writableDatabase.update(USER_TABLE_NAME, values, "$USER_EMAIL=?", arrayOf(user.user_email.toString()))
    }
    //LOGIN
    fun loginUser(email: String, pass: String): Boolean {
        val query = "SELECT * FROM $USER_TABLE_NAME WHERE $USER_EMAIL=? AND $USER_PASS=?"
        val whereArgs = arrayOf(email, pass)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, whereArgs)

        return cursor.count == 1
    }
    //REGISTER FUNCTIONS
    fun emailExists(email: String): Boolean {
        val query = "SELECT * FROM $USER_TABLE_NAME WHERE $USER_EMAIL=?"
        val whereArgs = arrayOf(email)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, whereArgs)
        val count = cursor.count

        cursor.close()
        return count >= 1
    }

    //PROD

    fun prodList(): ArrayList<Products> {
        val prodList: ArrayList<Products> = ArrayList()
        val selectQuery = "SELECT * FROM $PROD_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var name: String
        var desc: String
        var price: String
        var size: String
        var img: String

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(PROD_NAME))
                desc = cursor.getString(cursor.getColumnIndex(PROD_DESC))
                price = cursor.getString(cursor.getColumnIndex(PROD_PRICE))
                size = cursor.getString(cursor.getColumnIndex(PROD_SIZE))
                img = cursor.getString(cursor.getColumnIndex(PROD_IMG))

                val prod = Products(name, desc, size, price, img)
                prodList.add(prod)
            } while (cursor.moveToNext())
        }
        return prodList
    }

    fun addProd(prod: Products, db: SQLiteDatabase)
    {
        val values = ContentValues()
        values.put(PROD_NAME, prod.prod_name)
        values.put(PROD_DESC, prod.prod_desc)
        values.put(PROD_SIZE, prod.prod_size)
        values.put(PROD_PRICE, prod.prod_price)
        values.put(PROD_IMG, prod.prod_img)

        db.insert(PROD_TABLE_NAME, null, values)
    }
    //ORDER
    fun orderList(): ArrayList<Order> {
        val orderList: ArrayList<Order> = ArrayList()
        val selectQuery = "SELECT * FROM $ORDERS_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var qty: String
        var price: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ORDER_ID))
                name = cursor.getString(cursor.getColumnIndex(ORDER_NAME))
                qty = cursor.getString(cursor.getColumnIndex(ORDER_QTY))
                price = cursor.getString(cursor.getColumnIndex(ORDER_PRICE))
                val order = Order(id, name, qty, price)
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        return orderList
    }

    fun addOrder(order: Order)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ORDER_NAME, order.order_name)
        values.put(ORDER_QTY, order.order_qty)
        values.put(ORDER_PRICE, order.order_price)
        db.insert(ORDERS_TABLE_NAME, null, values)
    }

    fun deleteOrder(order: Order): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ORDER_ID, order.order_id)
        val success = db.delete(ORDERS_TABLE_NAME, ORDER_ID + "=" + order.order_id, null)

        return success
    }

    fun totalPrice(): Int {
        val orderList: ArrayList<Order> = ArrayList()
        var total: Int = 0
        val selectQuery = "SELECT * FROM $ORDERS_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0
        }
        var id: Int
        var name: String
        var qty: String
        var price: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ORDER_ID))
                name = cursor.getString(cursor.getColumnIndex(ORDER_NAME))
                qty = cursor.getString(cursor.getColumnIndex(ORDER_QTY))
                price = cursor.getString(cursor.getColumnIndex(ORDER_PRICE))
                total += price.toInt()
                val order = Order(id, name, qty, price)
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        return total
    }

}