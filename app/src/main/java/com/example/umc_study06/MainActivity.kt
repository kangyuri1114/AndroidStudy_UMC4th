package com.example.umc_study06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

   /* private val fl: FrameLayout by lazy {
        findViewById(R.id.fl_container)
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainBnv = findViewById<BottomNavigationView>(R.id.navigationView)

        mainBnv.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.navigation_Camera -> {
                        mainBnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_500)
                        mainBnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_500)
                        HomeFragment()
                    }
                    R.id.navigation_Home -> {
                        mainBnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.teal_700)
                        mainBnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.teal_700)
                        CameraFragment()
                    }
                    else -> {
                        mainBnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.teal_200)
                        mainBnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.teal_200)
                        PlantFragment()
                    }
                }
            )
            true
        }
        mainBnv.selectedItemId = R.id.navigation_Home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}