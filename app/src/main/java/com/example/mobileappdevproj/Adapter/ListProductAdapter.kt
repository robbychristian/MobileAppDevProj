package com.example.mobileappdevproj

import android.R.attr.name
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.Fragment.CartDialogFragment
import com.example.mobileappdevproj.Fragment.HomeFragment
import com.example.mobileappdevproj.Model.Order
import com.example.mobileappdevproj.Model.Products
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.single_item.view.*
import kotlinx.android.synthetic.main.dialog_addcart.view.rvCart
import java.lang.Integer.parseInt

lateinit var dbhelper: DBHelper_User

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
        var count = 0

        holder.addBtn.setOnClickListener {
            if (context is HomeActivity) {
                val name =  holder.tvName.text.toString()
                val qty = holder.tvQty.text.toString()
                val price = (holder.tvPrice.text.toString().replace("P", "").toInt() * qty.toInt()).toString()
                dbhelper = DBHelper_User(context)
                dbhelper.writableDatabase
                if (!qty.isEmpty() && qty != "0") {
                    Toast.makeText(context,"$name is Added to Cart!", Toast.LENGTH_SHORT).show()
                    val order = Order(count++, name, qty,price)
                    dbhelper.addOrder(order)
                    holder.tvQty.text.clear()
                } else {
                    Toast.makeText(context, "Input Quantity!", Toast.LENGTH_SHORT).show()
                }
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
        val tvQty = view.qty_input
        val addBtn = view.addToCartBtn_one
    }

}