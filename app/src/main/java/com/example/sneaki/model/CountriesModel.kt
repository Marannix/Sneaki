package com.example.sneaki.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesModel(
    val name: String,
    val capital: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
) : Parcelable