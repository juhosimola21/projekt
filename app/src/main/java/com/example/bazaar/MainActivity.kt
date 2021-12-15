package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav_view)


        bottomNavigation?.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id != R.id.listFragment ){
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }

//        bottomNavigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.fares_fragment -> {
//                    findNavController(R.id.myNavHostFragment).navigate()
//                }
//                R.id.market_fragment -> {
//                    findNavController(R.id.myNavHostFragment).navigate(R.id.myMarketFragment)
//                }
//            }
//            return@setOnItemSelectedListener true
//        }
    }

    override fun onDestroy(){
        super.onDestroy()
        MyApplication.token = ""
    }




}