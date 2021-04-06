package com.example.mobileappdevproj

import android.R.attr.name
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.Model.Products
import kotlinx.android.synthetic.main.single_item.view.*
import java.lang.Integer.parseInt


class ListProductAdapter(val context: Context, val items: ArrayList<Products>) : RecyclerView.Adapter<ListProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.single_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = items.get(position)
        holder.tvName.text = product.prod_name
        holder.tvDesc.text = product.prod_desc
        holder.ivProd.setImageResource(product.prod_img.toInt())
        holder.tvSize.text = product.prod_size
        holder.tvPrice.text = product.prod_price

        holder.addBtn.setOnClickListener {
            if (context is HomeActivity) {
                Toast.makeText(context, product.prod_name+ " Added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivProd = view.item_image
        val tvName = view.item_name
        val tvDesc = view.item_desc
        val tvSize = view.item_size
        val tvPrice = view.item_price
        val addBtn = view.addToCartBtn_one
    }

}