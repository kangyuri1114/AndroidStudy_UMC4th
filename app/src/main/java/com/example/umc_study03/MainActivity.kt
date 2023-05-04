package com.example.umc_study03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.umc_study03.databinding.ActivityMainBinding

/**
 * intent1 : 화면 전환
 * intent2 : 데이터 전달
 * intent3 : subActivity로부터 데이터 전달 받기
 * */
class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent1 = Intent(this, SubActivity::class.java)
        binding.btnGoSubActivity.setOnClickListener {
            startActivity(intent1)
        }

        val intent2 = Intent(this, SubActivity::class.java)
        intent2.putExtra("Data1", "Hello World")
        intent2.putExtra("Data2", 2023)
        binding.btnThrowValue.setOnClickListener {
            startActivity(intent2)
        }

        getResultText = registerForActivityResult (
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("Back")
                Toast
                    .makeText(this, "toast message: $mString", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnToast.setOnClickListener {
            val intent3 = Intent(this@MainActivity, SubActivity::class.java)
            getResultText.launch(intent3)
        }


    }
}