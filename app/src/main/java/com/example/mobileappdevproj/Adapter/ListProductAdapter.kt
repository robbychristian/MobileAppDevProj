package com.example.mobileappdevproj.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.HomeActivity
import com.example.mobileappdevproj.Model.Products
import com.example.mobileappdevproj.R
import java.lang.Integer.parseInt

class ListProductAdapter(val context: Context?, val items: ArrayList<Products>) : RecyclerView.Adapter<ListProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListProductAdapter.ViewHolder, position: Int) {
        val product = items.get(position)
        holder.tvName.text = product.prod_name
        holder.tvDesc.text = product.prod_desc
        holder.ivProd.setImageResource(parseInt(product.prod_img))
        holder.tvSize.text = product.prod_size
        holder.tvPrice.text = product.prod_price

        holder.addBtn.setOnClickListener {
            if (context is HomeActivity) {
                Toast.makeText(context, "Add to Cart working!", Toast.LENGTH_SHORT)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
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