package com.example.umc_study04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_study04.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding: ActivitySecondBinding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text = intent.getStringExtra("Memo")
    }
}