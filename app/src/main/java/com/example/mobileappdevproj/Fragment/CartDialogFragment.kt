package com.example.mobileappdevproj.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_addcart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ctx = context!!
        if (getOrderList().size > 0) {
            val orderAdapter = ListOrderAdapter(ctx, getOrderList())
            rvCart.adapter = orderAdapter
            rvCart.visibility = View.VISIBLE
            rvCart.layoutManager = LinearLayoutManager(activity)
        } else {
            rvItems.visibility = View.GONE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun getOrderList(): ArrayList<Order> {
        val dbHandler = DBHelper_User(context)
        val orderList: ArrayList<Order> = dbHandler.orderList()

        return orderList
    }
}