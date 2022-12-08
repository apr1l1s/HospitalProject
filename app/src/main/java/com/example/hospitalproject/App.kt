package com.example.hospitalproject

import android.app.Application
import com.example.hospitalproject.home.doctor.DoctorService

class App : Application() {
    val doctorService = DoctorService()

}