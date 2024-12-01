package com.example.anidb.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.anidb.fragments.Label

class HomeViewModel:ViewModel() {
    private var _label = mutableStateOf("Top Rated")
    val label = _label

    fun getLabelDetails(label:Label){
        _label.value = label.label
    }
}