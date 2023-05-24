package com.example.umc_study08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomDb = AppDatabase.getInstance(this)

        if(roomDb != null) {
            //val user = User("Yuri", 23)
            //val user = User("Riyu", 1)
            //roomDb.userDao().insert(user)

            //업데이트
            //roomDb.userDao().updateNameByUserId(1,"유리")

            //삭제
            val deleteUser = User("",0,2)
            roomDb.userDao().delete(deleteUser)

            val userList = roomDb.userDao().selectAll()
            Log.d("DB", "User List : ${userList}")

        }

    }
}