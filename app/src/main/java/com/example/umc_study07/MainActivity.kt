package com.example.umc_study07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.umc_study07.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding
            by lazy {ActivityMainBinding.inflate(layoutInflater)}

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

    val SET_TIME = 51
    val RESET = 52
    private val handler = object: Handler() {
        override fun handleMessage(msg: Message) { //UI에 접근 가능한 코드 블럭
            when(msg.what){
                SET_TIME -> { //화면을 세팅하는 것은 what이 time일 때만
                    binding.textTimer.text = formatTime(msg.arg1)
                }
                RESET -> {
                    binding.textTimer.text = "00 : 00"
                }
            }
        }
    }
    private fun start() {
        started = true //위에서 선언한 start 변수

        //subThread - UI 접근 금지 -> rinOnUiThread 사용
        thread(start=true) { //thread 내 start 함수
            var total = 0
            while (true) {
                Thread.sleep(1000) //1초에 한 번씩 작동 하도록
                if(!started) break //1초 딜레이 후 멈추는 것을 방지
                total += 1
                val msg = Message()
                msg.what = SET_TIME
                msg.arg1 = total //argument에 total 담음
                handler.sendMessage(msg)

//                runOnUiThread {
//                    binding.textTimer.text = formatTime(total)
//                }
            }
        }
    }

    private fun pause() {
        started = false
    }

    private fun stop() {
        started = false
        binding.textTimer.text = "00 : 00"
    }

    //시간 포맷
    private fun formatTime(time: Int): String {
        val minute = String.format("%02d", time/60) //분
        val second = String.format("%02d", time%60) //초
        return "$minute : $second"
    }
}