package com.example.comp3717_wo_miniapp

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Bookmarked dates/days.
 */
@Composable
fun Bookmarks(appNavController: NavController? = null) {
    Box {
        Text(text="This is the bookmarks page.")
    }
}