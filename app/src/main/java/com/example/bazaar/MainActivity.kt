package com.example.bazaar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var listViewModel: ListViewModel
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            true
        }

        R.id.action_search -> {
            val factory = ListViewModelFactory(Repository())
            listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

            val auxList = listViewModel.products.value
            val searchView: SearchView = item.actionView as SearchView
            searchView.queryHint = "Search product"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        listViewModel.products.value = auxList?.filter {
                            it.title.contains(
                                newText
                            )
                        } as MutableList<Product>?
                    } else {
                        listViewModel.products.value = auxList
                    }
                    return true
                }

            })
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }



}