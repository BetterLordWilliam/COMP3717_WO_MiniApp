package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comp3717_wo_miniapp.composables.ItemInfo.ItemInfo
import com.example.comp3717_wo_miniapp.composables.ItemListComposables.ItemListNavigator
import com.example.comp3717_wo_miniapp.composables.ItemListComposables.ItemRow
import com.example.comp3717_wo_miniapp.composables.ItemListComposables.ItemTypeSelector
import com.example.comp3717_wo_miniapp.states.EldenRingSavedViewModel


/**
 * collectAsStateWithLifecycle
 *
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
 *
 */
@Composable
fun SavedItemList() {
    val activityOwner = LocalContext.current as ViewModelStoreOwner
    val eldenRingSavedViewModel: EldenRingSavedViewModel = viewModel(viewModelStoreOwner = activityOwner)

    val selectedItemType    by eldenRingSavedViewModel.selectedItemType.collectAsStateWithLifecycle()
    val isLoading           by eldenRingSavedViewModel.isLoading.collectAsStateWithLifecycle()
    val error               by eldenRingSavedViewModel.error.collectAsStateWithLifecycle()
    val items               by eldenRingSavedViewModel.items.collectAsStateWithLifecycle()
    val infoItem            by eldenRingSavedViewModel.infoItem.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        ItemTypeSelector(
            selectedItemType = selectedItemType,
            onItemTypeSelected = eldenRingSavedViewModel.setItemType
        )
        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: $error", color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {
                Column {
                    ItemListNavigator(
                        eldenRingSavedViewModel.searchString,
                        eldenRingSavedViewModel.searchPage,
                        eldenRingSavedViewModel.incrementPage,
                        eldenRingSavedViewModel.decrementPage,
                        eldenRingSavedViewModel.updatedSearchString
                    )
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(items, key = { it.id }) { itemData ->
                            ItemRow(
                                itemData = itemData,
                                onButtonOneClicked = eldenRingSavedViewModel.showInfoItem,
                                onButtonTwoClicked = eldenRingSavedViewModel.deleteItem,
                                iconButtonTwo = Icons.Default.Delete
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        infoItem?.let { itemToShowInfo ->
            ItemInfo(
                itemData = itemToShowInfo,
                onDismissRequest = eldenRingSavedViewModel.dismissInfoItem
            )
        }
    }
}