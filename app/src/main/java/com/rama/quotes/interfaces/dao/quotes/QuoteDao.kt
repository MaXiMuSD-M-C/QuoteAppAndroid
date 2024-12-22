package com.rama.quotes.interfaces.dao.quotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): LiveData<List<QuoteEntity>>

    @Query("SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuote(): QuoteEntity?
}