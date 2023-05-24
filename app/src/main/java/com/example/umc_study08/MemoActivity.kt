package com.example.umc_study08

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MemoActivity : AppCompatActivity() {
    private lateinit var memoEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        memoEditText = findViewById(R.id.memoEditText)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val memoText = memoEditText.text.toString()
            val intent = Intent().apply {
                putExtra("memo", memoText)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
