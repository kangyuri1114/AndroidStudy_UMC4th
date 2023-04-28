package com.example.umc_study04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.umc_study04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //생명주기 예제
        Toast.makeText(this, "onCreate 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onCreate")
    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onStop")

    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy 호출 됨", Toast.LENGTH_LONG).show()
        Log.d("LifeCycle", "onDestroy")

    }
}