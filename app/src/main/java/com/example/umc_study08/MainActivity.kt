package com.example.umc_study08

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val SP_NAME = "my_sp_storage"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //writeSharedPreference("name", "마이클")
        val value = readSharedPreference("name") //값 꺼내기
        Log.d("프리퍼런스", "name=$value")
    }

    /**
     * SharedPreference 사용
     * - write와 read 두 개로 나누기
     * */
    fun writeSharedPreference(key: String, value:String) {
        val sp = getSharedPreferences(SP_NAME,Context.MODE_PRIVATE) //acticity가 가지고 있음
        val editor = sp.edit()
        editor.putString(key, value) //값 넣기
        editor.apply()
    }

    fun readSharedPreference(key: String) : String? { //read 시에는 editor 필요가 없다
        val sp = getSharedPreferences(SP_NAME,Context.MODE_PRIVATE) //preference 가져오기
        return sp.getString("name", "") //read, 값이 없을 때는 공백 처리를 하겠다고 하는 defalut value

    }
}