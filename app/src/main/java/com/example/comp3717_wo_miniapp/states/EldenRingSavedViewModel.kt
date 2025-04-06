package com.example.comp3717_wo_miniapp.states

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comp3717_wo_miniapp.ItemType
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.Shield
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.example.comp3717_wo_miniapp.data.models.Talisman
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.ArmourRepository
import com.example.comp3717_wo_miniapp.data.repositories.IncantationRepository
import com.example.comp3717_wo_miniapp.data.repositories.ItemsRepository
import com.example.comp3717_wo_miniapp.data.repositories.ShieldRepository
import com.example.comp3717_wo_miniapp.data.repositories.SorceryRepository
import com.example.comp3717_wo_miniapp.data.repositories.TalismanRepository
import com.example.comp3717_wo_miniapp.data.repositories.WeaponRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
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

    private val _searchString = MutableStateFlow<String>("")
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
            ) { itemString, itemPage, itemType ->
                Triple(itemType, itemString, itemPage)
            }.collectLatest { (itemType, itemString, itemPage) ->
                _isLoading.value = true
                loadItemsForTypeDB(itemType, itemString, itemPage)
                    .also{ _isLoading.value = false }
                    .collectLatest { fetchedItems ->
                        _items.value = fetchedItems as List<ItemData>
                    }
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

    val updatedSearchString: (String) -> Unit = { newTerms ->
        _searchString.value = newTerms
        _searchPage.value = 0
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
    val deleteItem: (ItemData) -> Unit = { infoItem ->
        println("Saving ${infoItem} ... (not really)")

        viewModelScope.launch {
            try {
                when (_selectedItemType.value) {
                    ItemType.WEAPON -> weaponRepository.removeItemFromDatabase(infoItem as Weapon)
                    ItemType.ARMOUR -> armourRepository.removeItemFromDatabase(infoItem as Armour)
                    ItemType.SHIELD -> shieldRepository.removeItemFromDatabase(infoItem as Shield)
                    ItemType.INCANTATION -> incantationRepository.removeItemFromDatabase(infoItem as Incantation)
                    ItemType.SORCERY -> sorceryRepository.removeItemFromDatabase(infoItem as Sorcery)
                    ItemType.TALISMAN -> talismanRepository.removeItemFromDatabase(infoItem as Talisman)
                    ItemType.ITEM -> itemRepository.removeItemFromDatabase(infoItem as Item)
                }

            } catch (exception: SQLiteConstraintException) {
                println("Error inserting item into the database: ${exception}")
            }
        }
    }

    private fun loadItemsForTypeDB(
        itemType: ItemType      = _selectedItemType.value,
        searchTerms: String     = _searchString.value,
        page: Int               = _searchPage.value

    ): Flow<List<Any>> {
        _isLoading.value    = true
        _error.value        = null
        _items.value        = emptyList()

        val res = when (itemType) {
            ItemType.WEAPON -> weaponRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.ARMOUR -> armourRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.SHIELD -> shieldRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.INCANTATION -> incantationRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.SORCERY -> sorceryRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.TALISMAN -> talismanRepository.getItemsFromDatabase(searchTerms, page)
            ItemType.ITEM -> itemRepository.getItemsFromDatabase(searchTerms, page)
        }

        _isLoading.value    = false
        _error.value        = null

        return res
    }
}