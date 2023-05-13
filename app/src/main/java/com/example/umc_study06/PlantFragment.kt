package com.example.umc_study06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_study06.databinding.FragmentPlantBinding
import me.relex.circleindicator.CircleIndicator3

class PlantFragment : Fragment() {

    private val viewBinding: FragmentPlantBinding by lazy{
        FragmentPlantBinding.inflate(layoutInflater)
    }
    private fun getDrawable():ArrayList<Int>{
        return arrayListOf<Int>(
            R.drawable.plant1,
            R.drawable.plant2,
            R.drawable.plant3)
    }
    lateinit var viewPagerImage: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPagerImage = viewBinding.viewpager
        viewPagerImage.adapter = ViewPagerAdapterImage(getDrawable())
        viewPagerImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator: CircleIndicator3 = viewBinding.indicator
        indicator.setViewPager(viewPagerImage)
        return viewBinding.root
    }
}