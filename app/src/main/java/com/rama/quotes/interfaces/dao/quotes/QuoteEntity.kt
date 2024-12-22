package com.rama.quotes.interfaces.dao.quotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = false)
    val idQuote: Int,
    val quote: String,
    val author: String,
    val category: Int
)