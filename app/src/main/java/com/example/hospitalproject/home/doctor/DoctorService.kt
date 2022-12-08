package com.example.hospitalproject.home.doctor

import com.github.javafaker.Faker

class DoctorService {
    private var doctors : List<Doctor>
    init{
        val faker  = Faker.instance()
        doctors = (1..100).map{
            Doctor(
                id = it.toLong(),
                name = faker.name().name(),
                photo = images[it % images.size],
                profession = professions[it % professions.size]
            )
        }
    }
    fun getDoctors():List<Doctor> = doctors
    companion object{
        private val professions = mutableListOf(
            "Кардиолог",
            "Хирург",
            "Терапев",
            "Уролог",
            "Стоматолог",
            "Аритмолог",
            "Эндокренолог"
        )
        private val images = mutableListOf(
            "https://mir-s3-cdn-cf.behance.net/project_modules/1400/cafeca101064001.5f1a123e0ddc3.jpg",
            "https://ae01.alicdn.com/kf/HTB1P_mXFH1YBuNjSszeq6yblFXam.jpg",
            "https://www.kino-teatr.ru/news/19542/175962.jpg",
            "https://damanhairstyles.com/wp-content/uploads/2019/07/Best-Short-Sides-Long-Top-Hairstyles.jpg",
            "https://econet.ru/uploads/pictures/493817/content_успокойся1_%281%29.jpg"
        )
    }
}