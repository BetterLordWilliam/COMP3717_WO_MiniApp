package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comp3717_wo_miniapp.states.EldenRingUIState

@Composable
private fun HomeOptionsDropDown(eldenRingUIState: EldenRingUIState? = null) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            eldenRingUIState?.itemStates?.map {
                DropdownMenuItem(text = { Text(it.key.enumValue) }, onClick = {
                    eldenRingUIState.selected.value = it.value
                })
            } ?: Text("No item types")
        }
    }
}

/**
 * App home page composable
 */
@Composable
fun Home(
    appNavController: NavController? = null,
    eldenRingUIState: EldenRingUIState? = null,
) {
    Column (
        modifier = Modifier
            .padding(4.dp)
    ) {
        Row {
            Text(text="Elden Ring equipment quick reference.")
            HomeOptionsDropDown(eldenRingUIState)
        }
        ItemList(eldenRingUIState?.selected?.value)
    }
}