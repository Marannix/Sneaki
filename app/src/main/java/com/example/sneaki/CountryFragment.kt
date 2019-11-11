package com.example.sneaki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {

    companion object {
        fun newInstance()  = CountryFragment()
    }

    private val response by lazy { CountryResponse() }
    private val adapter by lazy { CountryAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        response.execute()
        listView.adapter = adapter
        response.listOfCountries.observe(this, Observer { countries ->
            adapter.setData(countries)
        })
    }

}
