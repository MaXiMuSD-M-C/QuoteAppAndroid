package com.rama.quotes.interfaces.api

import com.rama.quotes.models.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("quotes")
    fun getQuotes(): Call<List<QuoteResponse>>
}