package com.d9.gps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.d9.gps.databinding.LayoutLocationItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class ListLocationAdapter : RecyclerView.Adapter<ListLocationAdapter.LocationViewHolder>() {
    private var listItem : MutableList<LocationData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = LayoutLocationItemBinding.inflate(LayoutInflater.from(parent.context))
        return LocationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.txtTime.text = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
        holder.txtLat.text = "${listItem[position].lat}"
        holder.txtLng.text = "${listItem[position].lng}"
        holder.txtAccuracy.text = "${listItem[position].accuracy}"
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun append(data : LocationData) {
        listItem.add(data)
        notifyItemChanged(listItem.size - 1)
    }

    class LocationViewHolder(itemView : View) : ViewHolder(itemView) {
        val txtTime : TextView = itemView.findViewById(R.id.tvTime)
        val txtLng : TextView = itemView.findViewById(R.id.tvLat)
        val txtLat : TextView = itemView.findViewById(R.id.tvLng)
        val txtAccuracy : TextView = itemView.findViewById(R.id.tvAccuracy)
    }

}