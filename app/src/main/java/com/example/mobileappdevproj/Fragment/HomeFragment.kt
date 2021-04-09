package com.example.mobileappdevproj.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.ListProductAdapter
import com.example.mobileappdevproj.Model.Products
import com.example.mobileappdevproj.R
import kotlinx.android.synthetic.main.fragment_home.*

lateinit var dbhelper: DBHelper_User

class HomeFragment: Fragment(R.layout.fragment_home) {
    companion object {
        const val ARG_EMAIL = "email"
        fun newInstance(email: String):HomeFragment{
            val fragment = HomeFragment()

            val bundle = Bundle().apply {
                putString(ARG_EMAIL, email)
            }
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dbhelper = DBHelper_User(context)
        cartBtn.setOnClickListener {
            val dialog = CartDialogFragment.newInstance(dbhelper.totalPrice())
            dialog.show(fragmentManager!!, "Cart")
        }

        val ctx = context!!
        if (getItemsList().size > 0) {
            val prodAdapter = ListProductAdapter(ctx, getItemsList())
            rvItems.adapter = prodAdapter
            rvItems.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE
            rvItems.layoutManager = LinearLayoutManager(activity)
        } else {
            rvItems.visibility = View.GONE
            tvNoRecordsAvailable?.visibility = View.VISIBLE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun getItemsList(): ArrayList<Products> {
        val dbHandler = DBHelper_User(context)
        val itemList: ArrayList<Products> = dbHandler.prodList()

        return itemList
    }

}