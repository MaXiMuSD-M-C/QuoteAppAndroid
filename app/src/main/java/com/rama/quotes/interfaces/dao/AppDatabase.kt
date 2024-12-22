package com.rama.quotes.interfaces.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rama.quotes.interfaces.dao.quotes.QuoteDao
import com.rama.quotes.interfaces.dao.quotes.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "quotes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}