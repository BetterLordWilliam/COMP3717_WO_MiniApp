package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * App home page composable
 */
@Composable
fun Home() {
    Column (
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text="Elden Ring equipment quick reference.")
        ItemList()
    }
}