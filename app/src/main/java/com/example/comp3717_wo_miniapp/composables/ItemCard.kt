package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.ItemCardUIState

@Composable
private fun MinimisedItemCard(
    item: ItemCardUIState
) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        AsyncImage(
            model = item.itemData.imageUrl,
            contentDescription = "${item.itemData.name} image.",
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
        )
        Card (
            colors = CardDefaults.cardColors(containerColor = Color(0xffdad3df)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                item {
                    Text(
                        text = item.itemData.name,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MaximisedItemCard(
    item: ItemCardUIState
) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        AsyncImage(
            model = item.itemData.imageUrl,
            contentDescription = "${item.itemData.name} image.",
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
        )
        Card (
            colors = CardDefaults.cardColors(containerColor = Color(0xffdad3df)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                item {
                    Text(
                        text = item.itemData.description,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    item: ItemCardUIState
) {
    Card (
        onClick = { item.expanded = !item.expanded; println(item.expanded) },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (item.expanded) 162.dp else 81.dp)
    ) {
        if (item.expanded) MaximisedItemCard(item) else MinimisedItemCard(item)
    }
}
