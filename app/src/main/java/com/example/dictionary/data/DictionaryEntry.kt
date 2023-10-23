package com.example.dictionary.data


data class DictionaryEntry(
    val meanings: List<Meaning>,
    val word: String,
    val phonetic: String,

    )
data class Definition(
    val definition: String,
    val example: String
)
data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)
