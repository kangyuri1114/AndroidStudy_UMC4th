package com.example.umc_study03

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_study03.databinding.ActivitySubBinding

/**
 * close Button : MainActivity로 이동
 * textView : MainActivity에서 데이터를 전송한 경우 화면에 나타남
 * viewBinding : ActivitySubBinding 사용
 * */

class SubActivity: AppCompatActivity() {
    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener {
            val intent3 = Intent(this, MainActivity::class.java).apply {
                putExtra("text", "text")
            }
            setResult(RESULT_OK, intent3)
            if (!isFinishing) finish()

        }
        binding.data1.text = intent.getStringExtra("Data1")
        binding.data2.text = "${intent.getIntExtra("Data2", 0)}"

        val intent4 = Intent(this, SubsubActivity::class.java)
        binding.btnGoSubsubActivity.setOnClickListener {
            startActivity(intent4)
        }
    }
}