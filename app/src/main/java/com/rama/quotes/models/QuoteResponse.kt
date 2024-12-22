package com.rama.quotes.models

import com.google.gson.annotations.SerializedName
import com.rama.quotes.interfaces.dao.quotes.QuoteEntity

data class QuoteResponse(
    @SerializedName("id_quote") val idQuote: Int,
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String,
    @SerializedName("category") val category: Int
)

fun QuoteResponse.toEntity(): QuoteEntity {
    return QuoteEntity(
        idQuote = this.idQuote,
        quote = this.quote,
        author = this.author,
        category = this.category
    )
}