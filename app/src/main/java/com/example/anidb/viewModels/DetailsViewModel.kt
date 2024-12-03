package com.example.anidb.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.anidb.R

class DetailsViewModel: ViewModel() {
    private val _toDo = mutableStateOf("About")
    val toDo = _toDo

    fun getToDo(toDo:String){
        _toDo.value = toDo
        Log.i("Abouts",_toDo.value)
    }
}