package com.example.mobileappdevproj

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdevproj.DBHelper.DBHelper_User
import com.example.mobileappdevproj.Fragment.CartDialogFragment
import com.example.mobileappdevproj.Model.Order
import kotlinx.android.synthetic.main.dialog_addcart.*
import kotlinx.android.synthetic.main.dialog_addcart.view.*
import kotlinx.android.synthetic.main.single_item.view.*
import kotlinx.android.synthetic.main.single_order.view.*


class ListOrderAdapter(val context: Context, val items: ArrayList<Order>) : RecyclerView.Adapter<ListOrderAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dbhelperUser = DBHelper_User(context)
        val price_update = CartDialogFragment.newInstance(dbhelperUser.totalPrice())
        price_update
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
        val dbhelperUser = DBHelper_User(context)
        holder.tvName.text = order.order_name
        holder.tvPrice.text = order.order_price
        holder.tvQty.text = order.order_qty

        holder.btnDel.setOnClickListener {
            val manager = (context as AppCompatActivity).supportFragmentManager
            dbhelperUser.deleteOrder(Order(order.order_id, holder.tvName.text.toString(), holder.tvPrice.text.toString(), holder.tvQty.text.toString()))

            val prev = manager.findFragmentByTag("Cart")
            if (prev != null) {
                val df: DialogFragment = prev as DialogFragment
                df.dismiss()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvQty = view.order_quantity
        val tvPrice = view.order_price
        val tvName = view.order_name
        val btnDel = view.deleteBtn
    }
}