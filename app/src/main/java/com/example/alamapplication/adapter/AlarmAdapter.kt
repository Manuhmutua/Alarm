package com.example.alamapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alamapplication.R
import com.example.alamapplication.model.Alarm

class AlarmAdapter(
    var mList: MutableList<Alarm>
) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var days = itemView.findViewById(R.id.days) as TextView
        var time = itemView.findViewById(R.id.time) as TextView
        var anotation = itemView.findViewById(R.id.anotation) as TextView
        var description = itemView.findViewById(R.id.description) as TextView
        var switch = itemView.findViewById(R.id.switch1) as Switch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        with(holder) {
            time.text = item.hour.toString() + ":" + item.minute
        }
    }
    
    fun updateData(list: MutableList<Alarm>) {
        mList = list
        notifyDataSetChanged()
    }

}