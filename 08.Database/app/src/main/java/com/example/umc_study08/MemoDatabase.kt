package com.example.umc_study08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Memo::class], version = 5)
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
                        .addMigrations(MIGRATION_4_5)
                        .build()
                }
            }
            return memoDatabase!!
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // memo_table 테이블의 스키마 변경 작업 수행
                database.execSQL("ALTER TABLE memo_table ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        private val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // memo_table 테이블의 스키마 변경 작업 수행
                database.execSQL("ALTER TABLE memo_table ADD COLUMN isFinished INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}