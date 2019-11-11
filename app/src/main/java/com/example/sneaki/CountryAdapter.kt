package com.example.sneaki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.country_row.view.*
import java.text.NumberFormat
import java.util.*

class CountryAdapter(private val context: Context) : BaseAdapter() {

    private var countries = emptyList<CountriesModel>()
    private var listener: OnCountrySelectedListener? = null

    interface OnCountrySelectedListener {
        fun onCountrySelected(country: CountriesModel)
    }

    fun setListener(listener: OnCountrySelectedListener) {
        this.listener = listener
    }

    fun setData(countries: List<CountriesModel>) {
        this.countries += countries
        notifyDataSetChanged()
    }

    // TODO: Find out what this does
    override fun getItem(position: Int): Any {
        return "Not Sure"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return countries.size
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val row = layoutInflater.inflate(R.layout.country_row, viewGroup, false)
        bind(row, position)
        return row
    }

    private fun bind(row: View, position: Int) {
        val formattedPopulation = NumberFormat.getNumberInstance(Locale.UK).format(countries[position].population)

        row.countryName.text = countries[position].name
        row.countryPopulation.text = formattedPopulation
        row.latitude.text = countries[position].latitude.toString()
        row.longitude.text = countries[position].longitude.toString()

        row.countryRow.setOnClickListener {
            listener?.onCountrySelected(countries[position])
        }

    }

    fun clearListener() {
        listener = null
    }
}