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
        row.countryName.text = countries[position].name
        val formattedPopulation = NumberFormat.getNumberInstance(Locale.UK).format(countries[position].population)
        row.countryPopulation.text =  formattedPopulation
        return row
    }

}