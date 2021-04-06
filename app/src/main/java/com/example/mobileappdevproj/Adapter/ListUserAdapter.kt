package com.example.mobileappdevproj.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.Fragment.HomeFragment
import com.example.mobileappdevproj.HomeActivity
import com.example.mobileappdevproj.Model.Products
import com.example.mobileappdevproj.Model.User
import com.example.mobileappdevproj.R
import java.lang.Integer.parseInt

class ListUserAdapter(val context: Context?, val users: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        holder.tvName.text = user.name
        holder.tvDesc.text = user.address
        //holder.ivProd.setImageResource(parseInt(product.prod_img))
        holder.tvSize.text = user.email
        holder.tvPrice.text = user.num

        holder.addBtn.setOnClickListener {
            if (context is HomeActivity) {
                Toast.makeText(context, "Add to Cart working!", Toast.LENGTH_SHORT)
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivProd = view.findViewById<ImageView>(R.id.item_image)
        val tvName = view.findViewById<TextView>(R.id.item_name)
        val tvDesc = view.findViewById<TextView>(R.id.item_desc)
        val tvSize = view.findViewById<TextView>(R.id.item_size)
        val tvPrice = view.findViewById<TextView>(R.id.item_price)
        val addBtn = view.findViewById<ImageView>(R.id.addToCartBtn_one)
    }

}