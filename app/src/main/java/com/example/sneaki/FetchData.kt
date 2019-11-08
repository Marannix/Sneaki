package com.example.sneaki

import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class FetchData : AsyncTask<Void, Void, Void>() {

    val string = StringBuilder()

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
        } catch (error: MalformedURLException) {
            error.printStackTrace()
        } catch (error: IOException) {
            error.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Log.d("yikes", result.toString())
    }
}