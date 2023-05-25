package com.example.umc_study08

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.umc_study08.databinding.ActivityMemoBinding


class MemoActivity : AppCompatActivity() {
    private val binding: ActivityMemoBinding by lazy { ActivityMemoBinding.inflate(layoutInflater)}
    private lateinit var memoEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val memoText = memoEditText.text.toString()
            val intent = Intent().apply {
                putExtra("memo", memoText)
            }
            //Activity 실행 결과 설정
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
