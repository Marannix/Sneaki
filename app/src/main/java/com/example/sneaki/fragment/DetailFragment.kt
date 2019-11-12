package com.example.sneaki.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sneaki.R
import com.example.sneaki.model.CountriesModel
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.NumberFormat
import java.util.*

private const val ARG_COUNTRY = "country"

class DetailFragment : Fragment() {

    private var country: CountriesModel? = null

    companion object {
        @JvmStatic
        fun newInstance(country: CountriesModel) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COUNTRY, country)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            country = it.getParcelable(ARG_COUNTRY)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun bind() {
        val formattedPopulation = NumberFormat.getNumberInstance(Locale.UK).format(country?.population)

        countryName.text = country?.name
        capitalCity.text = country?.capital
        countryPopulation.text =  formattedPopulation
        latitude.text = country?.latitude.toString()
        longitude.text = country?.longitude.toString()

    }
}
