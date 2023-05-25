package com.example.umc_study08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Memo::class], version = 3) // 변경: 버전 3로 업데이트
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var memoDatabase: MemoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MemoDatabase {
            if (memoDatabase == null) {
                synchronized(MemoDatabase::class.java) {
                    memoDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "app-database"
                    )
                        .addMigrations(MIGRATION_2_3) // 마이그레이션 설정 추가
                        .build()
                }
            }
            return memoDatabase!!
        }

        // 2 버전에서 3 버전으로의 마이그레이션 정의
        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 마이그레이션 로직 작성
                // 예시: 테이블 변경 등
            }
        }
    }
}