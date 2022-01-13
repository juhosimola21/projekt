package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.SettingsRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class ProfileUpdateViewModel(val repository: Repository) : ViewModel(){
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    fun updateUser() {
        viewModelScope.launch {
            val request = SettingsRequest(
                username = user.value!!.username,
                email = user.value!!.email,
                phone_number = user.value!!.phone_number
            )
            try {
                val result = repository.updateUser(MyApplication.token, request)
                Log.d("SettingsViewModel ok", "SettingsViewModel - #users:  $result")
            } catch (e: Exception) {

                Log.d("SettingsViewModel fail", "SettingsViewModel exception: ${e.toString()}")
            }
        }
    }
}