package com.example.hospitalproject.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hospitalproject.R
import android.widget.*
import com.bumptech.glide.Glide
import java.util.*


class ProfileFragment : Fragment(R.layout.fragment_profile){
    private lateinit var imageView: ImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById<ImageView>(R.id.appCompatImageView)
        imageView.setOnClickListener{
            pickImageGallery()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == IMAGE_REQUEST_CODE){
            imageView.setImageURI(data?.data)

        }
    }
    companion object{
         const val IMAGE_REQUEST_CODE = 1
    }
}