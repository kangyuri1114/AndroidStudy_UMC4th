package com.example.umc_study08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var memoDatabase: MemoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MemoDatabase? {
            if (memoDatabase == null) {
                synchronized(MemoDatabase::class.java) {
                    memoDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "app-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return memoDatabase
        }
    }
}