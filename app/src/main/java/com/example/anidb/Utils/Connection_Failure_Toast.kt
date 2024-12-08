package com.example.anidb.Utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

fun Connection_Failure_Toast(context: Context){
    Toast.makeText(context,"No Internet",Toast.LENGTH_SHORT).show()
}
