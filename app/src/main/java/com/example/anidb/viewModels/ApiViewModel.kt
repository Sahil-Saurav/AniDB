package com.example.anidb.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.anidb.api.NetworkResponse
import com.example.anidb.api.RetroFitInstance
import com.example.anidb.api.animeById.AnimeById
import com.example.anidb.api.animeCharacter.AnimeCharacter
import com.example.anidb.api.animeReview.AnimeReview
import com.example.anidb.api.animeSearch.AnimeDetailsbySearch
import com.example.anidb.api.recomendationAnime.Recommendations
import com.example.anidb.api.topAnime.TopAnime
import kotlinx.coroutines.launch

class ApiViewModel:ViewModel() {
    private val api = RetroFitInstance.api

    private val _animeDetailsBySearch = MutableLiveData<NetworkResponse<AnimeDetailsbySearch>>()
    val animeDetailsBySearch:LiveData<NetworkResponse<AnimeDetailsbySearch>> = _animeDetailsBySearch

    private val _animeDetailsById = MutableLiveData<NetworkResponse<AnimeById>>()
    val animeDetailsById : LiveData<NetworkResponse<AnimeById>> = _animeDetailsById

    private val _animeRecommendations = MutableLiveData<NetworkResponse<Recommendations>>()
    val animeRecommendations : LiveData<NetworkResponse<Recommendations>> = _animeRecommendations

    private val _animeTop = MutableLiveData<NetworkResponse<TopAnime>>()
    val animeTop : LiveData<NetworkResponse<TopAnime>> = _animeTop

    private val _animeReview = MutableLiveData<NetworkResponse<AnimeReview>>()
    val animeReview:LiveData<NetworkResponse<AnimeReview>> = _animeReview

    private val _animeCharacter= MutableLiveData<NetworkResponse<AnimeCharacter>>()
    val animeCharacter:LiveData<NetworkResponse<AnimeCharacter>> = _animeCharacter

    init {
        getRecentAnimeRecommendations()
        getTopAnime("bypopularity")
    }

    fun getAnimeSearch(name:String){
        //_animeDetailsBySearch.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getAnimeSearch(name)
            if (response.isSuccessful){
                Log.i("search",response.body().toString())
            }else{
                Log.i("Error",response.message())
            }
        }
    }

    fun getAnimeFullById(id:Int?){
        _animeDetailsById.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getAnimeById(id)
            try {
                if(response.isSuccessful){
                    Log.i("search",response.body().toString())
                    response.body()?.let {
                        _animeDetailsById.value = NetworkResponse.Success(it)
                    }
                }else{
                    Log.i("Error",response.message())
                    _animeDetailsById.value = NetworkResponse.Error("Something Went Wrong")
                }
            }catch (e:HttpException){
                _animeDetailsById.value = NetworkResponse.Error(e.message.toString())
            }
        }
    }

    fun getRecentAnimeRecommendations(){
        _animeRecommendations.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getRecommendations()
            try {
                if(response.isSuccessful){
                    response.body()?.let {
                        _animeRecommendations.value = NetworkResponse.Success(it)
                    }
                }else{
                    _animeRecommendations.value = NetworkResponse.Error("Something Went Wrong")
                }
            }catch (e:HttpException){
                _animeRecommendations.value = NetworkResponse.Error(e.message.toString())
            }
        }
    }

    fun getTopAnime(filter:String){
        _animeTop.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getPopularAnime(filter)
            try {
                if(response.isSuccessful){
                    response.body()?.let {
                        _animeTop.value = NetworkResponse.Success(it)
                    }
                }else{
                    _animeTop.value = NetworkResponse.Error("Something Went Wrong")
                }
            }catch (e:HttpException){
                _animeTop.value = NetworkResponse.Error(e.message.toString())
            }
        }
    }

    fun getAnimeReview(id:Int?){
        _animeReview.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getAnimeReview(id)
            try {
                if (response.isSuccessful){
                    response.body()?.let {
                       _animeReview.value = NetworkResponse.Success(it) 
                    }
                }else{
                   _animeReview.value = NetworkResponse.Error("Error Occurred")
                }
            }catch (e:HttpException){
                _animeReview.value = NetworkResponse.Error(e.message.toString())
            }
        }
    }

    fun getAnimeCharacters(id:Int?){
        _animeCharacter.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = api.getAnimeCharacter(id)
            try {
                if (response.isSuccessful){
                    response.body()?.let {
                        _animeCharacter.value = NetworkResponse.Success(it)
                    }
                    Log.i("Characters",response.body().toString())
                }else{
                    _animeCharacter.value = NetworkResponse.Error("Error occurred")
                    Log.i("Error",response.message())
                }
            }catch (e:HttpException){
                _animeCharacter.value = NetworkResponse.Error(e.message.toString())
            }
        }
    }
}