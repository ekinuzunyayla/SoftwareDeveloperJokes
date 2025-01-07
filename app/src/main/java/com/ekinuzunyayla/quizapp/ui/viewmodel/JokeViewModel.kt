package com.ekinuzunyayla.quizapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekinuzunyayla.quizapp.data.api.JokeApi
import com.ekinuzunyayla.quizapp.data.model.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeViewModel : ViewModel() {
    private val _jokes = MutableStateFlow<List<Joke>>(emptyList())
    val jokes: StateFlow<List<Joke>> = _jokes
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/atilsamancioglu/ProgrammingJokesJSON/main/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    private val api = retrofit.create(JokeApi::class.java)
    
    init {
        fetchJokes()
    }
    
    private fun fetchJokes() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                val fetchedJokes = api.getJokes()
                _jokes.value = fetchedJokes.filter { joke ->
                    when (joke.type) {
                        "single" -> joke.joke.isNotEmpty()
                        "twopart" -> joke.setup.isNotEmpty() && joke.delivery.isNotEmpty()
                        else -> false
                    }
                }
                println("Jokes fetched successfully: ${fetchedJokes.size} jokes")
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                println("Error fetching jokes: ${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
} 