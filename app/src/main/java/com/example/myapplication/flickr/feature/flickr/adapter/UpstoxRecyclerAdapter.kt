package com.example.myapplication.flickr.feature.flickr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.entity.Upstox
import com.example.myapplication.flickr.feature.flickr.adapter.UpstoxRecyclerAdapter.FlickrViewHolder
import kotlinx.android.synthetic.main.upstox_item.*

class UpstoxRecyclerAdapter : RecyclerView.Adapter<FlickrViewHolder>() {
    var upstoxList = ArrayList<Upstox>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
        return FlickrViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upstox_item,
                parent, false))
    }

    override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
        holder.onBind(upstoxList[position])
    }

    override fun getItemCount(): Int {
        return upstoxList.size
    }

    fun setItems(upstoxList: ArrayList<Upstox>) {
        this.upstoxList = upstoxList;
        notifyDataSetChanged()
    }

    class FlickrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(upstox: Upstox) {
            itemView.findViewById<TextView>(R.id.company_name).text = upstox.companyName
            itemView.findViewById<TextView>(R.id.ltp).text = "₹${upstox.ltp}"
            itemView.findViewById<TextView>(R.id.net_quantity).text = upstox.quantity.toString()
            itemView.findViewById<TextView>(R.id.profit_loss).text = "₹${(upstox.quantity * upstox.ltp)}"
        }
    }
}