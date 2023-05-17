package com.example.umc_study07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.umc_study07.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            start()
        }
        binding.btn2.setOnClickListener {
            pause()
        }
        binding.btn3.setOnClickListener {
            stop()
        }
    }

    private var started = false
    private var total = 0

    private fun start() {
        started = true

        lifecycleScope.launch {//코루틴 사용
            while (true) {
                delay(1000)
                if (!started) break
                total += 1
                updateTimer(total)
            }
        }
    }

    private fun pause() {
        started = false
    }

    private fun stop() {
        started = false
        total = 0
        updateTimer(total)
    }

    private fun updateTimer(time: Int) {
        binding.textTimer.text = formatTime(time)
    }

    private fun formatTime(time: Int): String {
        val minute = String.format("%02d", time / 60)
        val second = String.format("%02d", time % 60)
        return "$minute : $second"
    }
}