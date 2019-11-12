package com.example.sneaki.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.sneaki.adapter.CountryAdapter
import com.example.sneaki.data.CountryResponse
import com.example.sneaki.R
import com.example.sneaki.model.CountriesModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var listener: OnCountrySelectedListener? = null
    private var countries = emptyList<CountriesModel>()

    interface OnCountrySelectedListener {
        fun onCountrySelected(country: CountriesModel)
    }

    companion object {
        fun newInstance() = CountryFragment()
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
        initSpinner()
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

    private fun initSpinner() {
        val spinnerAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.sorts, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortSpinner.adapter = spinnerAdapter
        sortSpinner.onItemSelectedListener = this
    }

    private fun setCountryListener() {
        adapter.setListener(object : CountryAdapter.OnCountryClickedListener {
            override fun onCountryClicked(country: CountriesModel) {
                listener?.onCountrySelected(country)
            }
        })
    }

    private fun subscribeToViewState() {
        response.listOfCountries.observe(this, Observer { listOfCountries ->
            countries = listOfCountries
            adapter.setData(countries)
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        when (position) {
            0 -> {
                response.sortCountries(false)
            }
            1 -> {
                response.sortCountries(true)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onDetach() {
        super.onDetach()
        adapter.clearListener()
    }
}
