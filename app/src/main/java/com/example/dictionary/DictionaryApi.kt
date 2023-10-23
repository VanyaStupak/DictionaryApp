package com.example.dictionary

import com.example.dictionary.data.DictionaryEntry
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWord(@Path("word") word: String): List<DictionaryEntry>
}