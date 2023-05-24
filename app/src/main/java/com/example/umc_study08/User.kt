package com.example.umc_study08

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //이것이 room DB 에서 사용되는 객체임을 나타냄
data class User(

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userID") val userID: Int = 0 //고유 키
    //autoGenerate = true 사용 시 따로 입력하지 않아도 Insert할 때 마다 값이 자동으로 늘어남
)
