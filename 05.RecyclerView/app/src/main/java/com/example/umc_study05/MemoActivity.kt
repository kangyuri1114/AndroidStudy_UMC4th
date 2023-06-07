package com.example.umc_study05

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.umc_study05.Switch.SubActivity


class MemoActivity : AppCompatActivity() {
    private lateinit var memoEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var btn_todo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        memoEditText = findViewById(R.id.memoEditText)
        saveButton = findViewById(R.id.saveButton)
        btn_todo = findViewById(R.id.btn_todo)

        saveButton.setOnClickListener {
            val memoText = memoEditText.text.toString()
            val intent1 = Intent().apply {
                putExtra("memo", memoText)
            }
            setResult(Activity.RESULT_OK, intent1)
            finish()
        }

        btn_todo.setOnClickListener {
            val intent2 = Intent(this, SubActivity::class.java)
            startActivity(intent2)
        }
    }
}

