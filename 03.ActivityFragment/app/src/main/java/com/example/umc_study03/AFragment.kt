package com.example.umc_study03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class AFragment : Fragment() {

    //리스너 만들기

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val binding = FragmentABinding.inflate(inflater, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_a, container, false)

       //val result = binding.EditText.getText().toString()

    }
}