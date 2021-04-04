package com.example.mobileappdevproj.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.mobileappdevproj.Model.Products

class ListProductAdapter(internal var activity: Activity,
                         internal var listProd: List<Products>,
                         internal var edt_prod_id: EditText,
                         internal var edt_prod_name: EditText,
                         internal var edt_prod_desc: EditText,
                         internal var edt_prod_price: EditText,
                         internal var edt_prod_img: EditText): BaseAdapter(){

    internal var inflater: LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return listProd.size
    }

    override fun getItem(position: Int): Any {
        return listProd[position]
    }

    override fun getItemId(position: Int): Long {
        return listProd[position].prod_id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        TODO("Not yet implemented")
        //val rowView: View
        //rowView = inflater.inflate(R.layout.row_layout.)
    }
}