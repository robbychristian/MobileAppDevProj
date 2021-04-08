package com.example.mobileappdevproj

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.Model.Order
import kotlinx.android.synthetic.main.single_item.view.*
import kotlinx.android.synthetic.main.single_order.view.*

class ListOrderAdapter(val context: Context, val items: ArrayList<Order>) : RecyclerView.Adapter<ListOrderAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.single_order,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = items.get(position)
        holder.tvName.text = order.order_name
        holder.tvPrice.text = order.order_price
        holder.tvQty.text = order.order_qty
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvQty = view.order_quantity
        val tvPrice = view.order_price
        val tvName = view.order_name
    }
}