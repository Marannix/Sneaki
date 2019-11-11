package com.example.sneaki

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        Log.d("stuf", country!!.name)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}
