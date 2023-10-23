package com.example.dictionary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.data.DictionaryEntry
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val dictApi: DictionaryApi) : ViewModel() {
    val definitions = MutableLiveData<List<DictionaryEntry>>()

    fun getDefinitions(word: String) {
        viewModelScope.launch {
            val result: List<DictionaryEntry> = try {
                dictApi.getWord(word)
            } catch (e: HttpException){
                emptyList()
            }
            definitions.value = result
        }
    }
}
