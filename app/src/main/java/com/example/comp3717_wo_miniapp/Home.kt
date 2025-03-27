package com.example.comp3717_wo_miniapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.composables.ItemCard
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.Weapon

/**
 * App home page composable
 */
@Composable
fun Home(
    appNavController: NavController? = null,
    eldenRingUIState: EldenRingUIState? = null,
) {
    Column {
        Box {
            Text(text="Elden Ring equipment quick reference.")
        }
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            // modifier = Modifier.ver
        ) {
            eldenRingUIState?.weapons?.let {
                items(it) { item ->
                    ItemCard(item)
                }
            } ?: item {
                Text("Spinner ...")
            }
        }
    }
}