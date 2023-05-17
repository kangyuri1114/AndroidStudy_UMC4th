package com.example.umc_study07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.umc_study07.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var timerJob: Job? = null
    private var remainingTime = 0 // 초기값 0으로 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editText.inputType = android.text.InputType.TYPE_CLASS_NUMBER // 숫자 입력 타입으로 설정

        binding.btn1.setOnClickListener {
            val inputTime = binding.editText.text.toString().toIntOrNull()

            if (inputTime != null && inputTime > 0) {
                remainingTime = inputTime
                startTimer()
            }
        }
        binding.btn2.setOnClickListener {
            pauseTimer()
        }
        binding.btn3.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel() // 이전 타이머 작업이 실행 중인 경우 취소

        timerJob = lifecycleScope.launch {

            updateTimer(remainingTime)

            while (remainingTime > 0) {
                delay(1000)
                remainingTime -= 1
                updateTimer(remainingTime)

                if(remainingTime == 0) {
                    binding.textTimer.text = "타이머 종료"
                }
            }
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()
    }

    private fun stopTimer() {
        timerJob?.cancel()
        remainingTime = 0
        updateTimer(remainingTime)
        binding.textTimer.text = "타이머 종료"

    }

    private fun updateTimer(time: Int) {
        binding.textTimer.text = formatTime(time)
    }

    private fun formatTime(time: Int): String {
        val minute = String.format("%02d", time / 60)
        val second = String.format("%02d", time % 60)
        return "$minute:$second"
    }
}