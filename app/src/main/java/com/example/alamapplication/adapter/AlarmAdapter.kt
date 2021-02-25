package com.example.alamapplication.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.NonNull
//import androidx.recyclerview.widget.RecyclerView
//import com.example.alamapplication.R
//import com.example.alamapplication.model.Alarm
//
//class AlarmRecyclerViewAdapter() :
//    RecyclerView.Adapter<AlarmViewHolder>() {
//    private var alarms: List<Alarm>
//
//    @NonNull
//    override fun onCreateViewHolder(
//        @NonNull parent: ViewGroup,
//        viewType: Int
//    ): AlarmViewHolder {
//        val itemView: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
//        return AlarmViewHolder(itemView, listener)
//    }
//
//    override fun onBindViewHolder(
//        @NonNull holder: AlarmViewHolder,
//        position: Int
//    ) {
//        val alarm = alarms[position]
//        holder.bind(alarm)
//    }
//
//    override fun getItemCount(): Int {
//        return alarms.size
//    }
//
//    fun setAlarms(alarms: List<Alarm>) {
//        this.alarms = alarms
//        notifyDataSetChanged()
//    }
//
//    override fun onViewRecycled(@NonNull holder: AlarmViewHolder) {
//        super.onViewRecycled(holder)
//        holder.alarmStarted.setOnCheckedChangeListener(null)
//    }
//
//    init {
//        alarms = ArrayList()
//    }
//}