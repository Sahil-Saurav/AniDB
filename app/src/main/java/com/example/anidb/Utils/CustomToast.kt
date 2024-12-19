package com.example.anidb.Utils

import android.content.Context
import android.widget.Toast

fun CustomToast(context: Context,message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
