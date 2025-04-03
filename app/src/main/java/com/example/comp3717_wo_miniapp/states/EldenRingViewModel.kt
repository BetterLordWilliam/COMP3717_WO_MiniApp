package com.example.comp3717_wo_miniapp.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.comp3717_wo_miniapp.ItemType
import com.example.comp3717_wo_miniapp.data.ItemData
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

/**
 * Elden Ring quick reference application main view model.
 *
 * Application view model.
 */
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

//    private val _searchString = MutableStateFlow("")
//    val searchString: StateFlow<String> = _searchString.asStateFlow()

    val searchString    = MutableStateFlow("")

    /**
     * Load weapon items from the database by default.
     */
    init {
        itemPageSubscribe()
        itemTypeSubscribe()
        searchByTermsSubscribe()
    }

    /**
     * Sets the selected item type.
     *
     * @param itemType {ItemType} the type of item
     */
    fun setItemType(itemType: ItemType) {
        if (itemType == _selectedItemType.value)
            return

        _selectedItemType.value = itemType
        _searchPage.value = 0
        searchString.value = ""
    }

    fun incrementPage() {
        _searchPage.value += 1
    }

    fun decrementPage() {
        _searchPage.value -= if (_searchPage.value > 0) 1 else 0
    }

    /**
     * Shows a popup information dialog for the item information.
     *
     * @param infoItem {ItemData} the type of the items data
     */
    fun showInfoItem(infoItem: ItemData) {
        _infoItem.value = infoItem
    }

    /**
     * Dismisses a popup information dialog for the item information.
     */
    fun dismissInfoItem() {
        _infoItem.value = null
    }

    fun saveItem(infoItem: ItemData) {
        println("Saving ${infoItem} ... (not really)")
    }

    /**
     * call search when the item type is changed.
     */
    private fun itemTypeSubscribe() {
        viewModelScope.launch {
            selectedItemType
                .collect {
                    loadItemsForType()
                }
        }
    }

    /**
     * call search when the item page is changed.
     */
    private fun itemPageSubscribe() {
        viewModelScope.launch {
            searchPage
                .collect {
                    loadItemsForType()
                }
        }
    }

    /**
     * call search with the current collected value from the search string flow.
     */
    @OptIn(FlowPreview::class)
    private fun searchByTermsSubscribe() {
        viewModelScope.launch {
            searchString
                .debounce(1000L)
                .collect {
                    loadItemsForType()
                }
        }
    }

    /**
     * Uses the ItemType enum to query specific API endpoint for item information.
     *
     * @param itemType {ItemType} the type of item to query API for
     */
    private fun loadItemsForType(
        itemType: ItemType  = _selectedItemType.value,
        searchTerms: String = searchString.value,
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
                println(res)
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
