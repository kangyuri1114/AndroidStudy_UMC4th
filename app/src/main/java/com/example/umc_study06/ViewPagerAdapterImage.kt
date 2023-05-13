package com.example.umc_study06

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapterImage(var images:ArrayList<Int>)
    : RecyclerView.Adapter<ViewPagerAdapterImage.PagerViewHolder>() {
    inner class PagerViewHolder(parent: ViewGroup)
        :RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate((R.layout.fragment_image1),parent,false)){
        val img = itemView.findViewById<ImageView>(R.id.imgBanner1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.img.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}