package com.example.hospitalproject

import android.app.Application
import android.content.Context
import com.example.hospitalproject.model.DoctorService

class App : Application() {
    val doctorService = DoctorService()
}