package com.example.mobileappdevproj.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.LoginActivity
import com.example.mobileappdevproj.MainActivity
import com.example.mobileappdevproj.Model.Order
import com.example.mobileappdevproj.R
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutBtn.setOnClickListener {
            if(getOrderList().size > 0) {
                getOrderList().forEach {
                    dbhelper.deleteOrder(it)
                }
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                Toast.makeText(context, "You have successfully logged out!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "You have successfully logged out!", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

    }

    fun getOrderList(): ArrayList<Order> {
        val dbHandler = DBHelper_User(context)
        val orderList: ArrayList<Order> = dbHandler.orderList()
        return orderList
    }
}