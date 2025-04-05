package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.comp3717_wo_miniapp.states.EldenRingViewModel

/**
 * collectAsStateWithLifecycle
 *
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
 *
 */
@Composable
fun ItemList() {
    val activityOwner = LocalContext.current as ViewModelStoreOwner
    val eldenRingViewModel: EldenRingViewModel = viewModel(viewModelStoreOwner = activityOwner)

    val selectedItemType    by eldenRingViewModel.selectedItemType.collectAsStateWithLifecycle()
    val isLoading           by eldenRingViewModel.isLoading.collectAsStateWithLifecycle()
    val error               by eldenRingViewModel.error.collectAsStateWithLifecycle()
    val items               by eldenRingViewModel.items.collectAsStateWithLifecycle()
    val infoItem            by eldenRingViewModel.infoItem.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        ItemTypeSelector(
            selectedItemType = selectedItemType,
            onItemTypeSelected = eldenRingViewModel.setItemType
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
                        eldenRingViewModel.searchString,
                        eldenRingViewModel.searchPage,
                        eldenRingViewModel.incrementPage,
                        eldenRingViewModel.decrementPage,
                        eldenRingViewModel.updatedSearchString
                    )
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(items, key = { it.id }) { itemData ->
                            ItemRow(
                                itemData = itemData,
                                onButtonOneClicked = eldenRingViewModel.showInfoItem,
                                onButtonTwoClicked = eldenRingViewModel.saveItem
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
                onDismissRequest = eldenRingViewModel.dismissInfoItem
            )
        }
    }
}
