package com.example.hospitalproject

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.hospitalproject.model.DoctorService


class HomeFragment : Fragment(R.layout.fragment_home){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = (view.context.applicationContext as App).doctorService.getDoctors()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = DoctorAdapter()
        adapter.doctors = list
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }
}