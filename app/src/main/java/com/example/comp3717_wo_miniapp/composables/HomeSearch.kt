package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.comp3717_wo_miniapp.states.EldenRingUIState
import kotlinx.coroutines.launch

@Composable
fun HomeSearch(
    eldenRingUIState: EldenRingUIState? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val currentThing = eldenRingUIState?.selected?.value

    LaunchedEffect(currentThing?.searchTerms?.value) {
        currentThing?.page?.intValue = 0
        currentThing?.getItems()
    }

    Row (modifier = Modifier.fillMaxWidth()) {
        currentThing?.searchTerms?.value?.let {
            TextField(
                value = it,
                placeholder = { Text("Ermm") },
                onValueChange = { searchString ->
                    eldenRingUIState.selected.value.searchTerms.value = searchString
                },
                singleLine = true
            )
        }

        IconButton(onClick = {
            if (currentThing?.searchTerms?.value?.isNotBlank() == true) {
                eldenRingUIState.selected.value.page.intValue = 0
            }
        }) {
            Icon(Icons.Default.Search, contentDescription = "Search items")
        }

        Box {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                eldenRingUIState?.itemGroups?.map {
                    DropdownMenuItem(text = { Text(it.key) }, onClick = {
                        eldenRingUIState.selected.value = it.value
                    })
                } ?: Text("No item types")
            }
        }
    }
}