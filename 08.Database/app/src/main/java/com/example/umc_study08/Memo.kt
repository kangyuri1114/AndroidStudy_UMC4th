package com.example.umc_study08

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,
    var isFavorite: Boolean = false,
    var isFinished: Boolean = false
)
