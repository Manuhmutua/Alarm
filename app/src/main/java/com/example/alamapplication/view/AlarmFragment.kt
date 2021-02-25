package com.example.alamapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alamapplication.R
import com.example.alamapplication.model.Alarm
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AlarmFragment : Fragment() {

//    private lateinit var mAdapter: AlarmAdapter
    private lateinit var rvAlarms: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

        rvAlarms = view.findViewById(R.id.rvAlarms)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)

//        val list = arrayListOf<Alarm>()
//
//        val alarm1 = Alarm("1", "10:00", "AM", "Please Wake up", false)
//        val alarm2 = Alarm("2", "13:00", "PM", "ZZZZZ", false)
//        val alarm3 = Alarm("3", "10:50", "AM", "Please Wake up", false)
//        val alarm4 = Alarm("4", "11:00", "AM", "Collect Keys", false)
//
//        list.add(alarm1)
//        list.add(alarm2)
//        list.add(alarm3)
//        list.add(alarm4)
//
//        loadResults(list)
//
//        rvAlarms.adapter = mAdapter
        rvAlarms.layoutManager = LinearLayoutManager(context)

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_alarmFragment_to_addAlarmFragment)
        }

        return view
    }

    private fun loadResults(results: MutableList<Alarm>) {
//        mAdapter = AlarmAdapter(results)
//        rvAlarms.adapter = mAdapter
//        mAdapter.updateData(results)
    }
}