package com.example.anidb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {
    private val _search = mutableStateOf("")
    val search = _search

    private val _type = mutableStateOf("tv")
    val type = _type

    private var _idx = mutableStateOf(1)
    val idx = _idx

    fun setSearch(search:String){
        _search.value = search
    }
    fun getSearch():String{
        return _search.value
    }
    fun setType(type:String){
        _type.value = type
    }
    fun getType():String{
        return _type.value
    }
    fun setIndex(idx:Int){
        _idx.value = idx
    }
}