package com.rama.quotes.ui.quotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rama.quotes.interfaces.dao.quotes.QuoteDao
import com.rama.quotes.interfaces.dao.quotes.QuoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(application: Application, private val quoteDao: QuoteDao) : AndroidViewModel(application) {

    private val _randomQuote = MutableLiveData<QuoteEntity?>()
    val randomQuote: LiveData<QuoteEntity?> get() = _randomQuote

    init {
        getRandomQuote()
    }

    fun getRandomQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            val quote = quoteDao.getRandomQuote()
            _randomQuote.postValue(quote)
        }
    }
}