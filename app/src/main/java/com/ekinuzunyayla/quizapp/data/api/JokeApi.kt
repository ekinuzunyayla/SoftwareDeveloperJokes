package com.ekinuzunyayla.quizapp.data.api

import com.ekinuzunyayla.quizapp.data.model.Joke
import retrofit2.http.GET

interface JokeApi {
    @GET("jokes.json")
    suspend fun getJokes(): List<Joke>
} 