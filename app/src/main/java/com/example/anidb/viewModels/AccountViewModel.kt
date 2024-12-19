package com.example.anidb.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anidb.Utils.toUserDetails
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow

class AccountViewModel:ViewModel() {
    private val collection_Name= "users"
    private val db = Firebase.firestore
    private val database_Reference = db.collection(collection_Name)
    private var _data = mutableStateOf<UserDetails?>(null)
    val data = _data
    private val _userName = mutableStateOf("")
    fun insertUser(name: String,emailID: String){
        database_Reference.document(emailID).set(UserDetails(name = name,emailID=emailID))
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.i("insert","Insert Successful")
                }else{
                    Log.i("insert","Insert Failed")
                }
            }
    }

    fun addWatch_List(watchList: WatchList,emailID: String){
        database_Reference.document(emailID).update("watchList",FieldValue.arrayUnion(watchList))
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.i("added_watchList","Watchlist Added Successfully")
                }else{
                    Log.i("added_watchList","Failed to add watchlist")
                }
            }
    }

    fun removeWatch_List(watchList: WatchList,emailID: String){
        database_Reference.document(emailID).update("watchList",FieldValue.arrayRemove(watchList))
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.i("added_watchList","Watchlist Added Successfully")
                }else{
                    Log.i("added_watchList","Failed to add watchlist")
                }
            }
    }

    fun getUser(emailID: String){
        database_Reference.document(emailID).get()
            .addOnCompleteListener { document->
                if (document != null){
                    _data.value = document.result?.data?.toUserDetails()
                    Log.i("read_user","Document fetched Successfully${data.value}")
                }else{
                    _data.value = null
                    Log.i("read_user","Failed to fetch document")
                }
            }
            .addOnFailureListener { exception->
                Log.i("getUser",exception.message.toString())
            }
    }
    fun setUserName(userName:String){
        _userName.value = userName
    }
    fun getUserName():String{
        return _userName.value
    }
}

data class UserDetails(
    val name:String? = null,
    val emailID:String? = null,
    val watchList : List<WatchList>? = null
)

data class WatchList(
    val animeName:String? = null,
    val imageUrl:String? = null
)