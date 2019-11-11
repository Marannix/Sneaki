package com.example.sneaki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCountryFragment()
    }

    private fun initCountryFragment() {
        val fragment = CountryFragment.newInstance()
        replaceFragment(fragment)
    }

//    private fun initDetailFragment() {
//        val fragment = DetailFragment.newInstance()
//        replaceFragment(fragment)
//    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
