package com.huyduc1108.basemvvmkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huyduc1108.basemvvmkotlin.ui.main.data.Token
import com.huyduc1108.basemvvmkotlin.BuildConfig

@Database(
    entities = [Token::class],
    version = BuildConfig.VERSION_DATABASE,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
//    abstract fun nearDao(): NearDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //         Create and pre-populate the database. See this article for more details:
//         https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
                .build()
        }
    }
}