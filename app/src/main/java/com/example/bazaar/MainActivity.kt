package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print("dhfjkfhskghkjvhksjfhvg")
        Log.d("xsjfhdwgjkgsdv","sdjhcgjguiewrfer")
        val navController = findNavController(R.id.myNavHostFragment)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNavigation?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id != R.id.listFragment ){
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}