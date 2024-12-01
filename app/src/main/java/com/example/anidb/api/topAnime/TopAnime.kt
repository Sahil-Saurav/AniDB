package com.example.anidb.api.topAnime

data class TopAnime(
    val `data`: List<Data?>?,
    val pagination: Pagination?
)