package com.example.comp3717_wo_miniapp

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * App home page composable
 */
@Composable
fun Home(appNavController: NavController? = null) {
    Box {
        Text(text="Will Otterbein's awesome app!")
    }
}