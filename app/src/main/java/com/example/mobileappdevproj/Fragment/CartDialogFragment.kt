package com.example.mobileappdevproj.Fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.ListOrderAdapter
import com.example.mobileappdevproj.ListProductAdapter
import com.example.mobileappdevproj.Model.Order
import com.example.mobileappdevproj.R
import kotlinx.android.synthetic.main.dialog_addcart.*
import kotlinx.android.synthetic.main.fragment_home.*

class CartDialogFragment: DialogFragment() {

    companion object {
        private const val MY_INT = "my_int"
        private var total_price = 0

        fun newInstance(price_total: Int) = CartDialogFragment().apply{
            arguments = Bundle().apply {
                putInt(MY_INT, price_total)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt(MY_INT)?.let {
            total_price = it
        }
    }



    override fun onStart() {
        super.onStart()
        dbhelper = DBHelper_User(context)
        if (getOrderList().size > 0) {
            val orderAdapter = ListOrderAdapter(context!!, getOrderList())
            rvCart.adapter = orderAdapter
            rvCart.layoutManager = LinearLayoutManager(activity)
            totalPrice.text = "P" + total_price.toString()
        } else {
            Toast.makeText(context, "No Items to show!", Toast.LENGTH_SHORT).show()
        }
        cancelBtn.setOnClickListener {
            val manager = (context as AppCompatActivity).supportFragmentManager
            val prev = manager.findFragmentByTag("Cart")
            if (prev != null) {
                val df: DialogFragment = prev as DialogFragment
                df.dismiss()
            }
        }

        checkOutBtn.setOnClickListener {
            dbhelper = DBHelper_User(context)
            val total = dbhelper.totalPrice().toString()
            if (getOrderList().size > 0) {
                getOrderList().forEach {
                    dbhelper.deleteOrder(it)
                }
                Toast.makeText(context, "Your order has been confirmed. The price is P$total", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Your cart is empty", Toast.LENGTH_SHORT).show()
            }
            val manager = (context as AppCompatActivity).supportFragmentManager
            val prev = manager.findFragmentByTag("Cart")
            if (prev != null) {
                val df: DialogFragment = prev as DialogFragment
                df.dismiss()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_addcart, container, false)
    }

    fun getOrderList(): ArrayList<Order> {
        val dbHandler = DBHelper_User(context)
        val orderList: ArrayList<Order> = dbHandler.orderList()

        return orderList
    }
}