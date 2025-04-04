package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.comp3717_wo_miniapp.states.EldenRingViewModel

/**
 * Bookmarked dates/days.
 */
@Composable
fun SavedItems(
    appNavController: NavController? = null,
) {
    Box {
        Text(text="This is the bookmarks page.")
    }
}