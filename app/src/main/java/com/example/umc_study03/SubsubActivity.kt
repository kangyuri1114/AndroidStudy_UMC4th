package com.example.umc_study03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_study03.databinding.ActivitySubsubBinding

class SubsubActivity : AppCompatActivity() {
    private val binding:ActivitySubsubBinding by lazy {ActivitySubsubBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.finish.setOnClickListener { finish() }

    }


}