package com.example.umc_study06

import ViewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_study06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.pager.adapter = ViewPagerAdapter(this)
//
//        binding.pager.registerOnPageChangeCallback(
//            object : ViewPager2.OnPageChangeCallback() {
//
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    binding.navigationView.menu.getItem(position).isChecked = true
//
//
//                }
//            }
//        )

        binding.navigationView.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.navigation_Camera -> {
                        binding.navigationView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_500)
                        binding.navigationView.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_500)
                        CameraFragment()
                    }
                    R.id.navigation_Home -> {
                        binding.navigationView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.teal_700)
                        binding.navigationView.itemTextColor = ContextCompat.getColorStateList(this, R.color.teal_700)
                        HomeFragment()
                    }
                    else -> {
                        binding.navigationView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.teal_200)
                        binding.navigationView.itemTextColor = ContextCompat.getColorStateList(this, R.color.teal_200)
                        PlantFragment()
                    }
                }
            )
            true
        }
        binding.navigationView.selectedItemId = R.id.navigation_Home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}