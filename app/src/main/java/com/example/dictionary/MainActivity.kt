package com.example.dictionary

import DictionaryAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dictionary.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: DictionaryAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val dictApi = retrofit.create(DictionaryApi::class.java)

        adapter = DictionaryAdapter()
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(this)

        viewModel = MainViewModel(dictApi)

        binding.search.doAfterTextChanged {
            viewModel.getDefinitions(it.toString())
        }
//        binding.searchButton.setOnClickListener {
//            val word = binding.search.text.toString()
//            viewModel.getDefinitions(word)
//        }

        viewModel.definitions.observe(this, Observer { definitions ->
            adapter.setEntries(definitions)
        })
    }
}