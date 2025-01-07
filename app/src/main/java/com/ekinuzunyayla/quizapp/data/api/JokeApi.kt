package com.ekinuzunyayla.quizapp.data.api

import com.ekinuzunyayla.quizapp.data.model.Joke
import com.ekinuzunyayla.quizapp.data.model.JokeResponse
import retrofit2.http.GET

interface JokeApi {
    @GET("jokes.json")
    suspend fun getProgrammingJokes(): List<Joke>
    
    @GET("joke/Christmas?amount=10")
    suspend fun getChristmasJokes(): JokeResponse
} 