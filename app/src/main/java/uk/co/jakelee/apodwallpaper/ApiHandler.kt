package uk.co.jakelee.apodwallpaper

import android.graphics.BitmapFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL

fun getResponse(url: String): ApodResponse {
    val request = Request.Builder()
        .url(url)
        .get()
        .build()
    val response = OkHttpClient().newCall(request).execute()
    if (response.isSuccessful) {
        response.body()?.string()?.let {
            return Gson().fromJson(it, ApodResponse::class.java)
        }
        throw IOException()
    } else {
        throw IOException()
    }
}

fun getImage(image: String) = BitmapFactory.decodeStream(URL(image).openStream())

data class ApodResponse(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)