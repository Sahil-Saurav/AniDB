package com.example.anidb.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    private val _toDo = mutableStateOf("About")
    val toDo = _toDo

    private val _character_type = mutableStateOf("Main")
    val character_type = _character_type

    private val _idx = mutableStateOf(1)
    val idx = _idx

    fun setToDo(toDo:String){
        _toDo.value = toDo
        Log.i("Abouts",_toDo.value)
    }
    fun setCharacter_Type(type:String){
        _character_type.value = type
    }
    fun getCharacter_Type():String{
        return _character_type.value
    }
    fun setIndex(index:Int){
        _idx.value = index
    }
    fun getIndex():Int{
        return _idx.value
    }
}