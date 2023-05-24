package com.example.umc_study08

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.umc_study08.databinding.ActivityMainBinding

var data: String? = ""

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val text = binding.editText.text.toString()
            intent.putExtra("Memo", text)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()

        data = binding.editText.text.toString()
    }

    override fun onRestart() {
        super.onRestart()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("재작성 묻기")
        builder.setMessage("Memo 다시 작성하겠습니까?")
        builder.apply{
            setPositiveButton("재작성하기", DialogInterface.OnClickListener{ dialog, id -> })
            setNegativeButton("처음부터 쓰기", DialogInterface.OnClickListener{
                    dialog, id -> binding.editText.setText(null) })

        }

        builder.create()
        builder.show()
    }
}