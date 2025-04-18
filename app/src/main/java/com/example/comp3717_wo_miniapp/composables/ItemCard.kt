package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.data.entites.ItemData

@Composable
private fun CardActions(
    onInfoButton: () -> Unit,
    onSaveButton: () -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.Top
    ) {
        IconButton(onClick = {
            onInfoButton()
        }, modifier = Modifier
            .size(24.dp)
        ) {
            Icon(Icons.Default.Info, contentDescription="Item info page")
        }
        IconButton(onClick = {
            onSaveButton()
        }, modifier = Modifier
            .size(24.dp)
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription="Save item")
        }
    }
}

@Composable
private fun <T : ItemData> MinimisedItemCard(
    item: T
) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = "${item.name} image.",
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
        )
        Card (
            colors = CardDefaults.cardColors(containerColor = Color(0xffdad3df)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = item.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

//@Composable
//private fun <T : ItemData> MaximisedItemCard(
//    item: ItemCardState<T>,
//) {
//    Row (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(4.dp)
//    ) {
//        AsyncImage(
//            model = item.itemData.imageUrl,
//            contentDescription = "${item.itemData.name} image.",
//            modifier = Modifier
//                .fillMaxHeight()
//                .padding(4.dp)
//        )
//        Card (
//            colors = CardDefaults.cardColors(containerColor = Color(0xffdad3df)),
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            LazyColumn {
//                item {
//                    Text(
//                        text = item.itemData.description,
//                        fontSize = 20.sp,
//                        modifier = Modifier
//                            .padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun <T : ItemData> ItemCard(
    item: T,
    onInfoButton: () -> Unit,
    onSaveButton: () -> Unit
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MinimisedItemCard(item)
        CardActions(onInfoButton, onSaveButton)
    }
}
