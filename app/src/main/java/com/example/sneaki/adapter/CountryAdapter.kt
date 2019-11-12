package com.example.sneaki.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.sneaki.R
import com.example.sneaki.model.CountriesModel
import kotlinx.android.synthetic.main.country_row.view.*

class CountryAdapter(private val context: Context) : BaseAdapter() {

    private var countries = emptyList<CountriesModel>()
    private var listener: OnCountryClickedListener? = null

    interface OnCountryClickedListener {
        fun onCountryClicked(country: CountriesModel)
    }

    fun setListener(listener: OnCountryClickedListener) {
        this.listener = listener
    }

    fun setData(countries: List<CountriesModel>) {
        this.countries = countries
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
        row.countryName.text = countries[position].name
        row.countryRow.setOnClickListener {
            listener?.onCountryClicked(countries[position])
        }
    }

    fun clearListener() {
        listener = null
    }
}