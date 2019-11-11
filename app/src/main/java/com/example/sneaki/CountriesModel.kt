package com.example.sneaki

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesModel(
    val name: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
) : Parcelable