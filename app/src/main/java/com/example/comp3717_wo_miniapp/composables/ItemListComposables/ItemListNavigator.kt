package com.example.comp3717_wo_miniapp.composables.ItemListComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ItemListNavigator(

    searchField: MutableStateFlow<String>,
    searchPage: StateFlow<Int>,
    navigatorForward: () -> Unit,
    navigatorBackward: () -> Unit

) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = searchField.collectAsState().value,
            onValueChange = { searchField.value = it; }
        )
        IconButton(navigatorBackward) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Page")
        }
        Text(text = "${searchPage.collectAsState().value}")
        IconButton(navigatorForward) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next page")
        }
    }
}