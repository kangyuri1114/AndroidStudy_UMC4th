package com.example.umc_study08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//싱글톤 패턴
@Database(entities = [User::class], version=1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao //UserDao와 연결 코드

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if(appDatabase == null) {
                synchronized(AppDatabase::class.java) { //Synchronized -> 동기화, 현재 사용하고 있음을 나타냄
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}