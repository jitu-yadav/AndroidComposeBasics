package com.example.composebasics.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.composebasics.models.Quote

@Composable
fun QuoteList(data: Array<Quote>, onClick: (quote: Quote) -> Unit) {

    LazyColumn(content = {
        items(data) {
            QuoteListItem(it) { it ->
                onClick(it)
            }
        }
    })
}