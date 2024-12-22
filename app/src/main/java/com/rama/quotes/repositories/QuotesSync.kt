package com.rama.quotes.repositories

import android.content.Context
import com.rama.quotes.interfaces.api.APIService
import com.rama.quotes.interfaces.api.RetrofitInstance
import com.rama.quotes.interfaces.dao.AppDatabase
import com.rama.quotes.models.QuoteResponse
import com.rama.quotes.models.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class QuotesSync (context: Context) {

    fun getAllQuotes(context: Context) {
        val db = AppDatabase.getDatabase(context)
        val quoteDao = db.quoteDao()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<List<QuoteResponse>> =
                    RetrofitInstance.Retrofit().create(APIService::class.java)
                        .getQuotes().execute()

                if (response.isSuccessful) {
                    val listQuotes: List<QuoteResponse>? = response.body()

                    listQuotes?.let { quotesList ->
                        for (quoteResponse in quotesList) {
                            val quoteEntity = quoteResponse.toEntity()
                            quoteDao.insert(quoteEntity)
                        }
                        println("Quotes inserted successfully!")
                    }

                } else {
                    println("Error in API response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Error fetching quotes: ${e.message}")
            }
        }
    }

}