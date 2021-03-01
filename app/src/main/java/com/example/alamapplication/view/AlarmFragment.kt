package com.example.alamapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alamapplication.R
import com.example.alamapplication.adapter.AlarmAdapter
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.viewmodel.AlarmViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmFragment : Fragment() {

    lateinit var viewModel: AlarmViewModel
    var mAdapter: AlarmAdapter? = null

    private lateinit var rvAlarms: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(false)

        rvAlarms = view.findViewById(R.id.rvAlarms)
        floatingActionButton = view.findViewById(R.id.floatingActionButton)

        rvAlarms.adapter = mAdapter
        rvAlarms.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]
        viewModel.getAlarms()?.observe(viewLifecycleOwner) { alarms ->
            loadResults(alarms as MutableList<Alarm>)
        }

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_alarmFragment_to_addAlarmFragment)
        }

        return view
    }

    private fun loadResults(results: MutableList<Alarm>) {
        mAdapter = AlarmAdapter(results)
        rvAlarms.adapter = mAdapter
        mAdapter?.updateData(results)
    }

}