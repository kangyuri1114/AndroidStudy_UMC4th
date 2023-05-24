package com.example.umc_study08

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    //@Query : 직접 쿼리를 사용하는
    //조회
    @Query("SELECT * FROM User")
    fun selectAll(): List<User>

    @Query("SELECT * FROM User WHERE userID = :userID")
    fun selectByUserId(userID: Int): List<User>

    @Query("SELECT * FROM User WHERE name = :name")
    fun selectByUserName(name: String): List<User>

    //업데이트
    @Query("UPDATE User SET name = :name WHERE userID = :userID")
    fun updateNameByUserId(userID: Int, name: String)
}
