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
                array = array + CountriesModel(jsonObject.getString("name"))
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
        Log.d("yikes3", array.toString())
    }
}