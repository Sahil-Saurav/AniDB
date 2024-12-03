package com.example.anidb.api.animeReview

data class Data(
    val date: String?,
    val episodes_watched: Int?,
    val is_preliminary: Boolean?,
    val is_spoiler: Boolean?,
    val mal_id: Int?,
    val reactions: Reactions?,
    val review: String?,
    val score: Double?,
    val tags: List<String?>?,
    val type: String?,
    val url: String?,
    val user: User?
)