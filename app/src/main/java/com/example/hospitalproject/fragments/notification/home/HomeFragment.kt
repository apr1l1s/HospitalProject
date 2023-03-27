package com.example.hospitalproject.fragments.notification.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalproject.App
import com.example.hospitalproject.R
import com.example.hospitalproject.RegistrationActivity
import com.example.hospitalproject.doctor.DoctorAdapter


class HomeFragment : Fragment(R.layout.fragment_home){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = (view.context.applicationContext as App).doctorService.getDoctors()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = DoctorAdapter()
        adapter.onItemClick = {
            if (this.requireActivity().applicationContext!=null){
                val intent = Intent(this.requireActivity().applicationContext,RegistrationActivity::class.java)
                intent.putExtra("D", it)
                startActivity(intent)
            }
        }
        adapter.doctors = list
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }
}