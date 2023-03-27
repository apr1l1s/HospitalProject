package com.example.hospitalproject

import android.app.Application
import com.example.hospitalproject.doctor.DoctorService

class App : Application() {
    val doctorService = DoctorService()

}