package com.t3k.mobile.fams.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.core.view.MarginLayoutParamsCompat
import androidx.recyclerview.widget.RecyclerView
import com.t3k.mobile.fams.app.R
import com.t3k.mobile.fams.app.model.LocationModel
import com.t3k.mobile.fams.app.utility.getColorsfromtheme
import com.t3k.mobile.fams.app.utility.getTheme

class LocationListAdapter(var context: Context, var locationlist: MutableList<LocationModel>) :
    RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.locationlistitem, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return locationlist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = getColorsfromtheme(context, getTheme(context))
        holder.tvLocation.text =
            locationlist[position].LocationCode + "  " + locationlist[position].LocationName
        holder.tvLocation.setTextColor(color)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvLocation: TextView = v.findViewById(R.id.txtLocation)
    }
}