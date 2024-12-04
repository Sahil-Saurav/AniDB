package com.example.anidb.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailsViewModel: ViewModel() {
    private val _toDo = mutableStateOf("About")
    val toDo = _toDo

    fun setToDo(toDo:String){
        _toDo.value = toDo
        Log.i("Abouts",_toDo.value)
    }
}