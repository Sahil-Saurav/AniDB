package com.example.anidb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.anidb.R
import com.example.anidb.fragments.Label

class HomeViewModel:ViewModel() {
    private var _label = mutableStateOf("Top Rated")
    val label = _label

    private var _idx = mutableStateOf(1)
    val idx = _idx

    fun setLabelDetails(label:Label){
        _label.value = label.label
    }

    fun setIndex(idx:Int){
        _idx.value = idx
    }
}