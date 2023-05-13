package com.example.umc_study05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_study05.databinding.ActivityMemoBinding


class MemoActivity: AppCompatActivity() {

    private val binding: ActivityMemoBinding by lazy { ActivityMemoBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStore.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra("data", binding.edtTxt.text.toString())
            }

            setResult(RESULT_OK, intent)
            if(!isFinishing) finish()
        }
    }
}
