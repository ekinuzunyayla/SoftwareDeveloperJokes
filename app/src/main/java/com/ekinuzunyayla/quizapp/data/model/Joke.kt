package com.ekinuzunyayla.quizapp.data.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("joke")
    val joke: String = "",
    @SerializedName("setup")
    val setup: String = "",
    @SerializedName("delivery")
    val delivery: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("safe")
    val safe: Boolean = true
) 