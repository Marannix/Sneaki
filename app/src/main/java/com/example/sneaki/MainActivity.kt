package com.example.sneaki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {
    private val BACK_STACK_ROOT_TAG = "root_fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCountryFragment()
    }

    private fun initCountryFragment() {
        val fragment = CountryFragment.newInstance()
        replaceFragment(fragment)
        fragment.attach(object : CountryFragment.OnCountrySelectedListener {
            override fun onCountrySelected(country: CountriesModel) {
                initDetailFragment(country)
            }
        })
    }

    // TODO: When device rotated keep detail fragment state
    private fun initDetailFragment(country: CountriesModel) {
        val fragment = DetailFragment.newInstance(country)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(BACK_STACK_ROOT_TAG)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
