package com.example.comp3717_wo_miniapp.states

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comp3717_wo_miniapp.ItemType
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.ArmourRepository
import com.example.comp3717_wo_miniapp.data.repositories.IncantationRepository
import com.example.comp3717_wo_miniapp.data.repositories.ItemsRepository
import com.example.comp3717_wo_miniapp.data.repositories.ShieldRepository
import com.example.comp3717_wo_miniapp.data.repositories.SorceryRepository
import com.example.comp3717_wo_miniapp.data.repositories.TalismanRepository
import com.example.comp3717_wo_miniapp.data.repositories.WeaponRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

/**
 * Elden Ring quick reference application main view model.
 *
 * Application view model.
 */
@OptIn(FlowPreview::class)
class EldenRingViewModel (

    private val weaponRepository:       WeaponRepository,
    private val armourRepository:       ArmourRepository,
    private val shieldRepository:       ShieldRepository,
    private val sorceryRepository:      SorceryRepository,
    private val incantationRepository:  IncantationRepository,
    private val talismanRepository:     TalismanRepository,
    private val itemRepository:         ItemsRepository

): ViewModel() {

    // Keep these things mutable only withing this ViewModel
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#4

    private val _selectedItemType = MutableStateFlow(ItemType.WEAPON)
    val selectedItemType: StateFlow<ItemType> = _selectedItemType.asStateFlow()

    private val _items = MutableStateFlow<List<ItemData>>(emptyList())
    val items: StateFlow<List<ItemData>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _infoItem = MutableStateFlow<ItemData?>(null)
    val infoItem: StateFlow<ItemData?> = _infoItem.asStateFlow()

    private val _searchPage = MutableStateFlow<Int>(0)
    val searchPage: StateFlow<Int> = _searchPage.asStateFlow()

    private val _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString.asStateFlow()

    /**
     * Load weapon items from the database by default.
     */
    init {
        viewModelScope.launch {
            combine(
                _searchString.debounce(1000L),
                _searchPage,
                _selectedItemType
            ) { search, page, itemType ->
                Triple(itemType, search, page)
            }
            .collectLatest { (item: ItemType, search: String, page: Int) ->
                loadItemsForType(item, search, page)
            }
        }
    }

    /**
     * Sets the selected item type.
     *
     * @param itemType {ItemType} the type of item
     */
    val setItemType: (ItemType) -> Unit = { itemType ->
        if (itemType != _selectedItemType.value) {
            _selectedItemType.value = itemType
            _searchPage.value = 0
            _searchString.value = ""
        }
    }

    val incrementPage: () -> Unit = {
        _searchPage.value += 1
    }

    val decrementPage: () -> Unit = {
        _searchPage.value -= if (_searchPage.value > 0) 1 else 0
    }

    val updatedSearchString:  (String) -> Unit = { newTerms ->
        _searchString.value = newTerms
    }

    /**
     * Shows a popup information dialog for the item information.
     *
     * @param infoItem {ItemData} the type of the items data
     */
    val showInfoItem: (ItemData) -> Unit = { infoItem ->
        _infoItem.value = infoItem
    }

    /**
     * Dismisses a popup information dialog for the item information.
     */
    val dismissInfoItem: () -> Unit = {
        _infoItem.value = null
    }

    /**
     * Saves the item into the room database.
     */
    val saveItem: (ItemData) -> Unit = { infoItem ->
        println("Saving ${infoItem} ... (not really)")

        viewModelScope.launch {
            try {
                when (_selectedItemType.value) {
                    ItemType.WEAPON -> weaponRepository.saveItemToDatabase(infoItem as Weapon)
                    ItemType.ARMOUR -> armourRepository.saveItemToDatabase(infoItem as Armour)
                    else -> println("WOW bad")
                }

                // println(weaponRepository.getItemsFromDatabase())

            } catch (exception: SQLiteConstraintException) {
                println("Error inserting item into the database: ${exception}")
            }
        }
    }

    /**
     * Uses the ItemType enum to query specific API endpoint for item information.
     *
     * @param itemType {ItemType} the type of item to query API for
     */
    fun loadItemsForType(
        itemType: ItemType  = _selectedItemType.value,
        searchTerms: String = _searchString.value,
        page: Int           = _searchPage.value
    ) {
        viewModelScope.launch {
            _isLoading.value    = true
            _error.value        = null
            _items.value        = emptyList()

            try {
                val res: List<ItemData> = when (itemType) {
                    ItemType.WEAPON         -> weaponRepository.getItems(searchTerms, page)
                    ItemType.ARMOUR         -> armourRepository.getItems(searchTerms, page)
                    ItemType.SHIELD         -> shieldRepository.getItems(searchTerms, page)
                    ItemType.SORCERY        -> sorceryRepository.getItems(searchTerms, page)
                    ItemType.INCANTATION    -> incantationRepository.getItems(searchTerms, page)
                    ItemType.ITEM           -> itemRepository.getItems(searchTerms, page)
                    ItemType.TALISMAN       -> talismanRepository.getItems(searchTerms, page)
                }
                // println(res)
                _items.value = res

            } catch (e: Exception) {
                _error.value = "Epic fail bro ${e.localizedMessage}"
                _items.value = emptyList()

            } finally {
                _isLoading.value = false
            }
        }
    }
}
