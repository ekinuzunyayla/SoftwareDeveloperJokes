package com.ekinuzunyayla.quizapp.data.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("error")
    val error: Boolean = false,
    
    @SerializedName("id")
    val id: Int = 0,
    
    @SerializedName("type")
    val type: String = "",
    
    @SerializedName("category")
    val category: String = "Programming",
    
    @SerializedName("joke")
    val joke: String? = null,
    
    @SerializedName("setup")
    val setup: String? = null,
    
    @SerializedName("delivery")
    val delivery: String? = null,
    
    @SerializedName("safe")
    val safe: Boolean = true,
    
    @SerializedName("lang")
    val lang: String = "en",
    
    @SerializedName("flags")
    val flags: JokeFlags = JokeFlags()
) {
    // Type için güvenli kontrol
    fun isValidType(): Boolean = type in listOf("single", "twopart")
    
    // Single-type şaka için içerik kontrolü
    fun hasSingleJoke(): Boolean = type == "single" && !joke.isNullOrBlank()
    
    // Two-part şaka için içerik kontrolü
    fun hasTwoPartJoke(): Boolean = type == "twopart" && 
            !setup.isNullOrBlank() && !delivery.isNullOrBlank()
    
    // Şakanın gösterilmeye uygun olup olmadığını kontrol et
    fun isDisplayable(): Boolean = when(type) {
        "single" -> hasSingleJoke()
        "twopart" -> hasTwoPartJoke()
        else -> false
    }
    
    // Kategori adını güvenli bir şekilde al
    fun getCategoryText(): String = category.ifBlank { "Programming" }
    
    // Şaka metnini güvenli bir şekilde al
    fun getJokeText(): String {
        return when(type.lowercase()) {
            "single" -> joke?.trim() ?: "No joke available"
            "twopart" -> {
                val setupText = setup?.trim() ?: ""
                val deliveryText = delivery?.trim() ?: ""
                
                when {
                    setupText.isBlank() && deliveryText.isBlank() -> "No joke available"
                    setupText.isBlank() -> deliveryText
                    deliveryText.isBlank() -> setupText
                    else -> "$setupText\n\n$deliveryText"
                }
            }
            else -> "Invalid joke type"
        }
    }
}

data class JokeFlags(
    @SerializedName("nsfw")
    val nsfw: Boolean = false,
    
    @SerializedName("religious")
    val religious: Boolean = false,
    
    @SerializedName("political")
    val political: Boolean = false,
    
    @SerializedName("racist")
    val racist: Boolean = false,
    
    @SerializedName("sexist")
    val sexist: Boolean = false,
    
    @SerializedName("explicit")
    val explicit: Boolean = false
) {
    fun hasSensitiveContent(): Boolean = 
        nsfw || religious || political || racist || sexist || explicit
        
    fun getSensitiveContentTypes(): List<String> = buildList {
        if (nsfw) add("NSFW")
        if (religious) add("Religious")
        if (political) add("Political")
        if (racist) add("Racist")
        if (sexist) add("Sexist")
        if (explicit) add("Explicit")
    }
}  