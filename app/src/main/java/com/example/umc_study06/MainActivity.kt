package com.example.umc_study06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.umc_study06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val fragmentCamera by lazy { CameraFragment() }
    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentPlant by lazy { PlantFragment() }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavigationBar()
    }

    private fun initNavigationBar(){
        binding.navigationView.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.navigation_Camera -> {
                        changeFragment(fragmentCamera)
                    }
                    R.id.navigation_Home -> {
                        changeFragment(fragmentHome)
                    }
                    R.id.navigation_Plant -> {
                        changeFragment(fragmentPlant)
                    }
                }
                true
            }
            selectedItemId = R.id.navigation_Home
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}