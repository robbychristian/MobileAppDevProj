package com.example.mobileappdevproj.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.DBHelper.DBHelper_Prod
import com.example.mobileappdevproj.ListProductAdapter
import com.example.mobileappdevproj.Model.Products
import com.example.mobileappdevproj.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

    private fun getItemsList(): ArrayList<Products> {
        val dbHandler = DBHelper_Prod(context)
        val itemList: ArrayList<Products> = dbHandler.prodList()

        return itemList
    }
}