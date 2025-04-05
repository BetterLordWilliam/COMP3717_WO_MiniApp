package com.example.comp3717_wo_miniapp.states

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comp3717_wo_miniapp.ItemType
import com.example.comp3717_wo_miniapp.data.ItemData
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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class EldenRingSavedViewModel(

    private val weaponRepository: WeaponRepository,
    private val armourRepository: ArmourRepository,
    private val shieldRepository: ShieldRepository,
    private val sorceryRepository: SorceryRepository,
    private val incantationRepository: IncantationRepository,
    private val talismanRepository: TalismanRepository,
    private val itemRepository: ItemsRepository

): ViewModel() {

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
        loadItemsForTypeDB()
        searchByTermsSubscribe()
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
            searchString.value = ""
        }
    }

    val incrementPage: () -> Unit = {
        _searchPage.value += 1
    }

    val decrementPage: () -> Unit = {
        _searchPage.value -= if (_searchPage.value > 0) 1 else 0
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
     * call search with the current collected value from the search string flow.
     */
    @OptIn(FlowPreview::class)
    private fun searchByTermsSubscribe() {
        viewModelScope.launch {
            searchString
                .debounce(1000L)
                .collect {
                    loadItemsForTypeDB()
                }
        }
    }

    /**
     * call search with the page.
     */
    private fun itemPageSubscribe() {
        viewModelScope.launch {
            searchPage
                .collect {
                    loadItemsForTypeDB()
                }
        }
    }

    /**
     * Saves the item into the room database.
     */
    val deleteItem: (ItemData) -> Unit = { infoItem ->
        println("Saving ${infoItem} ... (not really)")

        viewModelScope.launch {
            try {
                when (_selectedItemType.value) {
                    ItemType.WEAPON -> weaponRepository.removeFromDatabase(infoItem as Weapon)
                    else -> println("WOW bad")
                }
                loadItemsForTypeDB()

            } catch (exception: SQLiteConstraintException) {
                println("Error inserting item into the database: ${exception}")
            }
        }
    }

    fun loadItemsForTypeDB(
        itemType: ItemType      = _selectedItemType.value,
        searchTerms: String     = searchString.value,
        page: Int               = _searchPage.value

    ) {
        viewModelScope.launch {
            _isLoading.value    = true
            _error.value        = null
            _items.value        = emptyList()

            try {
                val res: List<ItemData> = when (itemType) {
                    ItemType.WEAPON -> weaponRepository.getItemsFromDatabase(searchTerms, page)
                    else -> emptyList()
                }
                println(res)
                _items.value = res

            } catch (e: Exception) {
                println("Exception loading items from the database... ${e.localizedMessage}")
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}