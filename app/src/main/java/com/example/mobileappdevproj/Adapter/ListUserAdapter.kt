package com.example.mobileappdevproj.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.mobileappdevproj.Model.User

class ListUserAdapter(internal var activity: Activity,
                      internal var listUser: List<User>,
                      internal var edt_id: EditText,
                      internal var edt_name: EditText,
                      internal var edt_email: EditText,
                      internal var edt_pass: EditText,
                      internal var edt_num: EditText): BaseAdapter() {

    internal val inflater: LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return listUser.size
    }

    override fun getItem(position: Int): Any {
        return listUser[position]
    }

    override fun getItemId(position: Int): Long {
        return listUser[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }


}