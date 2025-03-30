package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.comp3717_wo_miniapp.states.EldenRingUIState

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
        HomeSearch(eldenRingUIState)
        ItemList(eldenRingUIState?.selected?.value)
    }
}