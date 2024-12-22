package com.rama.quotes.ui.quotes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rama.quotes.databinding.FragmentQuotesBinding
import com.rama.quotes.interfaces.dao.AppDatabase

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var quotesViewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = AppDatabase.getDatabase(requireContext()).quoteDao()
        quotesViewModel =
            ViewModelProvider(this, PhraseViewModelFactory(requireActivity().application, dao))[QuotesViewModel::class.java]

        quotesViewModel.randomQuote.observe(viewLifecycleOwner) { quoteEntity ->
            quoteEntity?.let {
                binding.tvQuote.text = it.quote
                binding.tvAuthor.text = it.author
            }
        }

        binding.btnNextQuote.setOnClickListener {
            quotesViewModel.getRandomQuote()
        }

        binding.tvQuote.setOnClickListener {
            quotesViewModel.getRandomQuote()
        }

        binding.btnShareQuote.setOnClickListener {
            val quote = binding.tvQuote.text.toString()
            val author = binding.tvAuthor.text.toString()
            shareQuote(quote, author)
        }

    }

    private fun shareQuote(quote: String, author: String) {
        val shareText = "\"$quote\" - $author" // Texto a compartir

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Compartir cita con"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}