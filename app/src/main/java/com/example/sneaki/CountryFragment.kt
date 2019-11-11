package com.example.sneaki

import android.os.Bundle
import android.util.Log
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
        fetchData()
        initListView()
        setCountryListener()
        subscribeToViewState()
    }

    private fun fetchData() {
        response.execute()
    }

    private fun initListView() {
        listView.adapter = adapter
    }

    private fun setCountryListener() {
        adapter.setListener(object: CountryAdapter.OnCountrySelectedListener {
            override fun onCountrySelected(country: CountriesModel) {
                Log.d("Countryfragment",country.name)
            }
        })
    }

    private fun subscribeToViewState() {
        response.listOfCountries.observe(this, Observer { countries ->
            adapter.setData(countries)
        })
    }

    override fun onDetach() {
        super.onDetach()
        adapter.clearListener()
    }
}
