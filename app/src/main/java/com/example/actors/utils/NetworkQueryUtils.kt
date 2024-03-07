package com.example.actors.utils

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkQueryUtils @Inject constructor(){
    fun  getResponseFromHttpUrl(url:URL):String{
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        return try {
            val stream: InputStream = urlConnection.inputStream
            val scanner = Scanner(stream)
            scanner.useDelimiter("//A")
            val hasInput: Boolean = scanner.hasNext()
            var response = ""
            if (hasInput){
                response = scanner.next()
            }
            scanner.close()
            response
        } finally {
            urlConnection.disconnect()
        }
    }

}
