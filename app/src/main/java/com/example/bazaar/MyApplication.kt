package com.example.bazaar

import android.app.Application
import com.example.bazaar.utils.SessionManager

class MyApplication: Application(){
    companion object{
        var token: String = ""
    }
}