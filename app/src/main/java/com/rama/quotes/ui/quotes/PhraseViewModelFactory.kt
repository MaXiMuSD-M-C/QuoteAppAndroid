package com.rama.quotes.ui.quotes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rama.quotes.interfaces.dao.quotes.QuoteDao

class PhraseViewModelFactory(
    private val application: Application,
    private val quoteDao: QuoteDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(application, quoteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}