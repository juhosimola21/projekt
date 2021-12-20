package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
            if(destination.id != R.id.listFragment && destination.id != R.id.myFaresFragment && destination.id != R.id.myMarketFragment && destination.id != R.id.profileFragment){
                bottomNavigation.visibility = View.GONE
            } else {
                bottomNavigation.visibility = View.VISIBLE
            }
        }

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fares_fragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.myFaresFragment)
                }
                R.id.market_fragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.myMarketFragment)
                }
                R.id.profile_fragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                }
                R.id.timeline_fragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
                }

            }
            return@setOnItemSelectedListener true
        }

    }


    override fun onDestroy(){
        super.onDestroy()
        MyApplication.token = ""
        MyApplication.username = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
//        R.id.action_search -> {
//            findNavController(R.id.nav_host_fragment).navigate(R.id.myFaresFragment)
//        }
//        R.id.action_filter -> {
//            findNavController(R.id.nav_host_fragment).navigate(R.id.myMarketFragment)
//        }
//            else -> {
//                super.onOptionsItemSelected(item)
//            }
//    }



}