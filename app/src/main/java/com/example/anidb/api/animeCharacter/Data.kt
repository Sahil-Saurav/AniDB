package com.example.anidb.api.animeCharacter

data class Data(
    val character: Character?,
    val role: String?,
    val voice_actors: List<VoiceActor>?
)