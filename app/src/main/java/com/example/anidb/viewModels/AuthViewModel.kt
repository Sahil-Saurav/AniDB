package com.example.anidb.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
    private val api = FirebaseAuth.getInstance()
    private val _authstate = MutableLiveData<AuthState>()
    val authstate = _authstate

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus(){
        if(api.currentUser==null){
            _authstate.value = AuthState.Unauthenticated
        }else{
            _authstate.value = AuthState.Authenticated
        }
    }

    fun signIn(email:String,pass:String){
        if(email.isEmpty() || pass.isEmpty()){
            _authstate.value = AuthState.Error("Email or Password can't be Empty")
            return
        }
        _authstate.value = AuthState.Loading
        api.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                    task->
                if(task.isSuccessful){
                    _authstate.value = AuthState.Authenticated
                }else{
                    _authstate.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signUp(email: String,pass: String){
        if (email.isEmpty() || pass.isEmpty()){
            _authstate.value = AuthState.Error("Email or Password can't be Empty")
            return
        }
        _authstate.value = AuthState.Loading
        api.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                    task->
                if (task.isSuccessful){
                    _authstate.value = AuthState.Authenticated
                }else{
                    _authstate.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signOut(){
        api.signOut()
        _authstate.value = AuthState.Unauthenticated
    }

}


sealed class AuthState{
    object Authenticated:AuthState()
    object Unauthenticated:AuthState()
    object Loading:AuthState()
    data class Error(val message:String):AuthState()
}