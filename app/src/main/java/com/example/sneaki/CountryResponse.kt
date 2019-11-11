package com.example.sneaki

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

// Wanted this to be my viewmodel
class CountryResponse : AsyncTask<Void, Void, Void>() {

    private val string = StringBuilder()
    var array = emptyList<CountriesModel>()
    var listOfCountries = MutableLiveData<List<CountriesModel>>()
    var isReversed = false
    private var latitude = 0.0
    private var longitude = 0.0

    override fun doInBackground(vararg p0: Void?): Void? {
        try {
            val url = URL("https://restcountries.eu/rest/v2/all")
            val connection = url.openConnection() as HttpURLConnection
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line = bufferedReader.readLine()
            while (line != null) {
                string.append(line)
                line = bufferedReader.readLine()
            }

            val jsonArray = JSONArray(string.toString())
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val name = jsonObject.getString("name")
                val capital = jsonObject.getString("capital")
                val population = jsonObject.getInt("population")
                val latlngArray = jsonObject.getJSONArray("latlng")

                if (latlngArray.length() > 0) {
                    latitude = latlngArray[0] as Double
                    longitude = latlngArray[1] as Double
                }

                array = array + CountriesModel(name, capital, population, latitude, longitude)
            }

        } catch (error: MalformedURLException) {
            error.printStackTrace()
        } catch (error: IOException) {
            error.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        listOfCountries.postValue(array)
    }

    fun sortCountries(reverse: Boolean) {
        if (listOfCountries.value.isNullOrEmpty()) {
            return
        }
        when (reverse) {
            true -> {
                isReversed = true
                listOfCountries.value = listOfCountries.value?.asReversed()!!
            }
            false -> {
                // My awful attempt to reverse a reversed list
                if (isReversed) {
                    listOfCountries.value = listOfCountries.value?.asReversed()!!
                }
                isReversed = false
            }
        }
    }
}