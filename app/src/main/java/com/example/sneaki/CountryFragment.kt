package com.example.sneaki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {

    private var listener: OnCountrySelectedListener? = null

    interface OnCountrySelectedListener {
        fun onCountrySelected(country: CountriesModel)
    }

    companion object {
        fun newInstance()  = CountryFragment()
    }

    private val response by lazy { CountryResponse() }
    private val adapter by lazy { CountryAdapter(requireContext()) }

    fun attach(listener: OnCountrySelectedListener) {
        this.listener = listener
    }

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
        if (response.listOfCountries.value.isNullOrEmpty()) {
            response.execute()
        }
    }

    private fun initListView() {
        listView.adapter = adapter
    }

    private fun setCountryListener() {
        adapter.setListener(object: CountryAdapter.OnCountryClickedListener {
            override fun onCountryClicked(country: CountriesModel) {
                listener?.onCountrySelected(country)
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
