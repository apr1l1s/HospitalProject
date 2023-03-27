package com.example.hospitalproject.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hospitalproject.R
import com.example.hospitalproject.databinding.ItemDoctorBinding

class DoctorAdapter: RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){

    var onItemClick: ((Doctor)->Unit)? = null

    var doctors:List<Doctor> = emptyList()
    class DoctorViewHolder(
        val binding:ItemDoctorBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoctorBinding.inflate(inflater,parent,false)
        return DoctorViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        with(holder.binding){
            holder.itemView.tag = doctor
            doctorNameTextView.text = doctor.name
            doctorProfessionTextView.text = doctor.profession
            if (doctor.photo.isNotBlank()){
                Glide.with(photoImageView.context)
                    .load(doctor.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_profile)
                    .error(R.drawable.ic_profile)
                    .into(photoImageView)
            } else {
                photoImageView.setImageResource(R.drawable.ic_profile)
            }
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(doctor)
        }
    }
    override fun getItemCount(): Int = doctors.size
}