package com.example.bazaar.viewmodels

import java.net.URI
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository
import com.example.bazaar.utils.SessionManager
import kotlinx.coroutines.launch
import okhttp3.*
import java.net.URL


class LoginViewModel(val repository: Repository) : ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

//    fun login() {
//        viewModelScope.launch {
//            val request =
//                LoginRequest(username = user.value!!.username, password = user.value!!.password)
//            try {
//                val result = repository.login(request)
//                MyApplication.token = result.token
//                token.value = result.token
//                Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
//            }catch(e: Exception){
//                Log.d("xxx", "MainViewModel - exception: ${e.toString()}")
//            }
//        }
//    }

    suspend fun login() {
        //Log.d("xsjfhdwgjkgsdv","menjel bazdmeg")
        val request =
            LoginRequest(username = user.value!!.username, password = user.value!!.password)
        //Log.d("usernameaaa",user.value!!.username)
        //Log.d("passwordaaa", user.value!!.password)
        try {
            val result = repository.login(request)
            MyApplication.token = result.token
            MyApplication.username = result.username
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }

    }
}

