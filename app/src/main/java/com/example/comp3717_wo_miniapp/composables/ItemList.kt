package com.example.comp3717_wo_miniapp.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comp3717_wo_miniapp.states.EldenRingItemGroup
import com.example.comp3717_wo_miniapp.data.ItemData
import kotlinx.coroutines.launch

@Composable
fun <T : ItemData> CounterRow(
    eldenRingItemGroup: EldenRingItemGroup<T>?
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            eldenRingItemGroup?.decrementPage()
            coroutineScope.launch {
                eldenRingItemGroup?.getItems()
            }
        }) { // Assuming you have access to your ViewModel
            Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = "${eldenRingItemGroup?.page?.intValue}")

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = {
            eldenRingItemGroup?.incrementPage()
            coroutineScope.launch {
                eldenRingItemGroup?.getItems()
            }
        }) {
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun <T : ItemData> ItemList(
    eldenRingItemGroup: EldenRingItemGroup<T>?,
) {
    Box (
        modifier = Modifier
            .fillMaxHeight()
    ) {
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(eldenRingItemGroup!!.items) {
                ItemCard(it)
            }
        }
        Surface (
            shadowElevation = 8.dp,
            color = Color(0x00FFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            CounterRow(eldenRingItemGroup)
        }
    }
}