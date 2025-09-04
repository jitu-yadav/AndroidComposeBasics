package com.example.composebasics

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.composebasics.models.Quote
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()
    var isDataLoaded = mutableStateOf(false)

    var currentPage = mutableStateOf(Pages.LISTING)
    var currentQuote : Quote? = null

    fun loadAssetFromFile(context: Context) {
        try {
            val inputStream = context.assets.open("quotes.json")
            val size = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            val json = String(byteArray, Charsets.UTF_8)

            val gson = Gson()
            data = gson.fromJson(json, Array<Quote>::class.java)

            Log.i("JITU", data.toString())
            isDataLoaded.value = true
        } catch (ex: Exception) {
            Log.e("JITU", ex.toString())
        }
    }

    fun switchPage(quote: Quote?) {
        if (currentPage.value == Pages.LISTING) {
            currentQuote = quote
            currentPage.value = Pages.DETAILS
        } else {
            currentPage.value = Pages.LISTING
        }
    }
}