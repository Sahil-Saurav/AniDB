package com.example.anidb.api

import com.example.anidb.api.animeById.AnimeById
import com.example.anidb.api.animeCharacter.AnimeCharacter
import com.example.anidb.api.animeReview.AnimeReview
import com.example.anidb.api.animeSearch.AnimeDetailsbySearch
import com.example.anidb.api.recomendationAnime.Recommendations
import com.example.anidb.api.topAnime.TopAnime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanAPI {

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") name:String
    ):Response<AnimeDetailsbySearch>

    @GET("recommendations/anime")
    suspend fun getRecommendations():Response<Recommendations>

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id:Int?
    ):Response<AnimeById>

    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("filter") filter:String
    ):Response<TopAnime>

    @GET("anime/{id}/reviews?preliminary=true")
    suspend fun getAnimeReview(
        @Path("id") id:Int?
    ):Response<AnimeReview>

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacter(
        @Path("id") id:Int?
    ):Response<AnimeCharacter>
}
