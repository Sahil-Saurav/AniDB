package com.example.anidb.api.recomendationAnime

data class Data(
    val content: String?,
    val entry: List<Entry?>?,
    val mal_id: String?,
    val user: User?
)