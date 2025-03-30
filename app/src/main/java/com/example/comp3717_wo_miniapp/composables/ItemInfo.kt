package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Item specific information page.
 */
@Composable
fun ItemInfo(navController: NavController? = null) {
    Box {
        Text(text="This is the Item Info Page.")
    }
}