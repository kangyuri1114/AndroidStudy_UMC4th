package com.example.umc_study03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_study03.databinding.ActivitySubsubBinding

class SubsubActivity : AppCompatActivity() {
    private val binding:ActivitySubsubBinding by lazy {ActivitySubsubBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewA()

        binding.btnSwitch.setOnClickListener { viewA() }
        binding.btnRemove.setOnClickListener { viewB() }
        binding.btnBack.setOnClickListener { finish() }

        /**
         *supportFragmentManager
        .setFragmentResultListener("requestKey", this) { requestKey, bundle ->
        val result = bundle.getString("bundleKey")
        }
         * */

    }

    private fun viewA() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, AFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun viewB() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, BFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}