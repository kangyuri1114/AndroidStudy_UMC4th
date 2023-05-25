package com.example.umc_study08

import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo_table")
    fun getAllMemos(): List<Memo>

    @Insert
    fun insertMemo(memo: Memo)

    @Update
    fun updateMemo(memo: Memo)

    @Delete
    fun deleteMemo(memo: Memo)
}