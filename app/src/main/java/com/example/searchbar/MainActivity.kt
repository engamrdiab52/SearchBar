package com.example.searchbar

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private val listOfBags: List<Bag> = sourceOfBags.listOfBags
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                val filterdList = listOfBags.filter {
                    query?.let { it1 ->
                        it.name_product?.contains(
                            it1,true
                        )
                    }!!
                }

                Log.d(TAG, filterdList.toString())

                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                // Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { Log.d(TAG, it) }
                val filterdList = listOfBags.filter {
                    newText?.let { it1 ->
                        it.name_product?.contains(
                            it1,true
                        )
                    }!!
                }

                Log.d(TAG, filterdList.toString())
                return true
            }

        })
        return true
    }
}