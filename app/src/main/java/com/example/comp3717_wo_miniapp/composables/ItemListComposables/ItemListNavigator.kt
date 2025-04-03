package com.example.comp3717_wo_miniapp.composables.ItemListComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comp3717_wo_miniapp.states.EldenRingViewModel

@Composable
fun ItemListNavigator() {
    val eldenRingViewModel: EldenRingViewModel = viewModel()

    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = eldenRingViewModel.searchString.collectAsState().value,
            onValueChange = { eldenRingViewModel.searchString.value = it; }
        )
        IconButton({ eldenRingViewModel.decrementPage() }) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Page")
        }
        Text(text = "${eldenRingViewModel.searchPage.collectAsState().value}")
        IconButton({ eldenRingViewModel.incrementPage() }) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next page")
        }
    }
}