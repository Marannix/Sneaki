package com.example.sneaki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

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
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(BACK_STACK_ROOT_TAG)
            .commit()
    }

    // TODO: Fix when screen is on Country Fragment and back button is pressed the screen becomes blank :)))))
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}