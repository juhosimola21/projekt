package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Product
import com.example.bazaar.model.RegisterRequest
import com.example.bazaar.model.User
import com.example.bazaar.model.UserInfo
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class ProfileViewModel(val repository: Repository) : ViewModel() {
    var user: MutableLiveData<UserInfo> = MutableLiveData() //elmentem az adatot

    init{
        Log.d("xxx", "ProfileViewModel constructor - Username: ${MyApplication.username}")
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            try {
                val result =repository.getProfileInfo(MyApplication.username)
                Log.d("rsult","$result")
                user.value = result.data[0]
                Log.d("xxx", "ProfileViewModel - #username:  ${result}")
            }catch(e: Exception){
                Log.d("xxx", "ProfileViewModel exception: ${e.toString()}")
            }
        }
    }
}