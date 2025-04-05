package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Bookmarked dates/days.
 */
@Composable
fun SavedItems() {
    Column (
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text="This is the bookmarks page.")
        SavedItemList()
    }
}