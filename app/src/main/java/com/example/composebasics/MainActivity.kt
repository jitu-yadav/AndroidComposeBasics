package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.composebasics.screens.QuoteDetails
import com.example.composebasics.screens.QuoteListScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadDataFromAsset()

        setContent {
            App()
        }
    }

    private fun loadDataFromAsset() {
        lifecycleScope.launch(Dispatchers.IO) {
            DataManager.loadAssetFromFile(applicationContext)
        }
    }
}

@Composable
fun App() {
    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentPage.value == Pages.LISTING) {
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPage(it)
            }
        } else {
            DataManager.currentQuote?.let { QuoteDetails(it) }
        }
    } else {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)) {
            Text("Loading...",
                style = MaterialTheme.typography.labelLarge)
        }
    }
}

enum class Pages {
    LISTING,
    DETAILS
}