package com.example.umc_study07

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.example.umc_study07.databinding.ActivityMusicPlayerBinding
import java.util.concurrent.TimeUnit

class MusicPlayerActivity : AppCompatActivity() {

    private val binding: ActivityMusicPlayerBinding
            by lazy { ActivityMusicPlayerBinding.inflate(layoutInflater) }

    private var isPlaying = false
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTimer.setOnClickListener {
            goTimer()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.unavailable)
        val currentPosition = mediaPlayer.currentPosition

        //음악 재생 완료 시
        mediaPlayer.setOnCompletionListener {
            isPlaying = false
            binding.seekBar.progress = 0
            handler.removeCallbacks(updateSeekBar())
        }
        //SeekBar
        binding.seekBar.max = mediaPlayer.duration
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.playButton.setOnClickListener {
            if(isPlaying) {
                pauseMusic()
            }
            else playMusic()
        }
        binding.stopButton.setOnClickListener {
            stopMusic()
            binding.seekBar.progress = 0
            binding.currentTime.text = formatTime(currentPosition)

        }

        //핸들러 초기화
        handler = Handler(Looper.getMainLooper())

    }

    private fun playMusic() {
        binding.seekBar.progress = 0
        mediaPlayer.start()
        isPlaying = true
        handler.postDelayed(updateSeekBar(), 0)
    }

    private fun pauseMusic() {
        mediaPlayer.pause()
        isPlaying = false
        handler.removeCallbacks(updateSeekBar())
    }

    private fun stopMusic() {
        mediaPlayer.seekTo(0)
        mediaPlayer.pause()
        binding.seekBar.progress = 0
        isPlaying = false
        handler.removeCallbacks(updateSeekBar())

    }

    private fun updateSeekBar() = object : Runnable  {
        //Runnable 객체는 run() 메서드를 오버라이드하여 실행될 코드를 정의
        override fun run() {
            val currentPosition = mediaPlayer.currentPosition
            binding.seekBar.progress = currentPosition
            binding.currentTime.text = formatTime(currentPosition)
            handler.postDelayed(this, 1000) //1초 후 실행
        }
    }

    private fun formatTime(time: Int): String {
        val minute = TimeUnit.MILLISECONDS.toMinutes(time.toLong())
        val second = TimeUnit.MILLISECONDS.toSeconds(time.toLong()) -
                TimeUnit.MINUTES.toSeconds(minute)
        return String.format("%02d : %02d", minute, second)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacks(updateSeekBar())
    }
    private fun goTimer() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
