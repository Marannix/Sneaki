package com.example.sneaki

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class CountryResponse : AsyncTask<Void, Void, Void>() {

    private val string = StringBuilder()
    var array = emptyList<CountriesModel>()
    var listOfCountries = MutableLiveData<List<CountriesModel>>()
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
                val population = jsonObject.getInt("population")
                val latlngArray = jsonObject.getJSONArray("latlng")

                if (latlngArray.length() > 0) {
                    latitude = latlngArray[0] as Double
                    longitude = latlngArray[1] as Double
                }

                array = array + CountriesModel(name, population, latitude, longitude)
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
}