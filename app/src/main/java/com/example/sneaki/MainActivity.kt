package com.example.sneaki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val response by lazy { CountryResponse() }
    private val adapter by lazy { CountryAdapter(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        response.execute()

        listView.adapter = adapter

        response.listOfCountries.observe(this, Observer { countries ->
            adapter.setData(countries)
        })
    }
}
