package com.innobuzz.postapp.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.innobuzz.postapp.model.DataPost
import com.innobuzz.postapp.model.TypeConverterDataUser

@Database(entities = [DataPost::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterDataUser::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppDao(): AppDao

    companion object {
        private var DB_INSTANCE: AppDatabase? = null

        fun getAppDBInstance(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "APP_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            Log.d("logging", "DB created")
            return DB_INSTANCE!!
        }
    }
}