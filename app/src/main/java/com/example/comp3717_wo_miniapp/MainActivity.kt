package com.example.comp3717_wo_miniapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comp3717_wo_miniapp.composables.Home
import com.example.comp3717_wo_miniapp.composables.SavedItems
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.eldenRingHttpClient
import com.example.comp3717_wo_miniapp.data.repositories.ArmourRepository
import com.example.comp3717_wo_miniapp.data.repositories.IncantationRepository
import com.example.comp3717_wo_miniapp.data.repositories.ItemsRepository
import com.example.comp3717_wo_miniapp.data.repositories.ShieldRepository
import com.example.comp3717_wo_miniapp.data.repositories.SorceryRepository
import com.example.comp3717_wo_miniapp.data.repositories.TalismanRepository
import com.example.comp3717_wo_miniapp.data.repositories.WeaponRepository
import com.example.comp3717_wo_miniapp.states.EldenRingUIState
import com.example.comp3717_wo_miniapp.states.itemstates.ArmourItemActions
import com.example.comp3717_wo_miniapp.states.itemstates.BasicItemActions
import com.example.comp3717_wo_miniapp.states.itemstates.EldenRingItemState
import com.example.comp3717_wo_miniapp.states.itemstates.IncantationItemActions
import com.example.comp3717_wo_miniapp.states.itemstates.SorceryItemActions
import com.example.comp3717_wo_miniapp.states.itemstates.WeaponItemActions
import com.example.comp3717_wo_miniapp.states.itemstates.old.ShieldItemActions

/**
 * Will Otterbein
 * A01371608
 *
 * API:
 * https://docs.eldenring.fanapis.com/docs
 *
 * [X]  App idea, as of yet unknown, wanting to
 *      Elden Ring equipment reference -- quick reference for Spells, Incantations, Weapons, Armour, etc
 * [X]  Setup the navigation scaffolding today.
 * [ ]  Possibly the room db setup as well
 *          (partially, documented the planned setup for the room db, implement next)
 *          (not too much because I need to figure out the structure of db).
 * [X]  Determine API to use for data
 * [ ]  UI Implementation
 */

data class NavItem(val icon: ImageVector, val navRoute: String)

/**
 * Enum to store keys.
 */
enum class ItemType(val enumValue : String) {
    WEAPON("weapons"),
    ARMOUR("armours"),
    SHIELD("shields"),
    SORCERY("sorceries"),
    INCANTATION("incantations"),
    TALISMAN("talismans"),
    ITEM("items")
}

class MainActivity : ComponentActivity() {

    private val weaponRepo      by lazy { WeaponRepository(eldenRingHttpClient) }
    private val armourRepo      by lazy { ArmourRepository(eldenRingHttpClient) }
    private val shieldRepo      by lazy { ShieldRepository(eldenRingHttpClient) }
    private val sorceryRepo     by lazy { SorceryRepository(eldenRingHttpClient) }
    private val incantationRepo by lazy { IncantationRepository(eldenRingHttpClient) }
    private val talismanRepo    by lazy { TalismanRepository(eldenRingHttpClient) }
    private val itemsRepo       by lazy { ItemsRepository(eldenRingHttpClient) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val eldenRingItemStates = remember {
                mapOf(
                    ItemType.WEAPON         to EldenRingItemState(weaponRepo, WeaponItemActions()),
                    ItemType.ARMOUR         to EldenRingItemState(armourRepo, ArmourItemActions()),
                    ItemType.SHIELD         to EldenRingItemState(shieldRepo, ShieldItemActions()),
                    ItemType.SORCERY        to EldenRingItemState(sorceryRepo, SorceryItemActions()),
                    ItemType.INCANTATION    to EldenRingItemState(incantationRepo, IncantationItemActions()),
                    ItemType.TALISMAN       to EldenRingItemState(talismanRepo, BasicItemActions()),
                    ItemType.ITEM           to EldenRingItemState(itemsRepo, BasicItemActions())
                )
            }

            val eldenRingUIState = remember { EldenRingUIState(eldenRingItemStates) }

            LaunchedEffect(eldenRingUIState.selected.value) {
                eldenRingUIState.selected.value?.getItems()
            }

            MainContent(eldenRingUIState)
        }
    }
}

/**
 * Composable to represent the applications navigation bar
 */
@Composable
fun BottomNavBar(navController: NavController) {

    // Define the navigation bar items
    val navItems = listOf(
        NavItem(Icons.Sharp.Home, "home"),
        NavItem(Icons.Sharp.Menu, "Bookmarks")
    )

    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.popBackStack() },
            icon = { Icon(Icons.Sharp.ArrowBack, contentDescription = null) }
        )
        navItems.forEach { item: NavItem ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(item.navRoute) },
                icon = { Icon(item.icon, contentDescription = null) }
            )
        }
    }
}

/**
 * Main content composable.
 * Define and organize the navigation structure for the application.
 */
@Composable
fun MainContent(eldenRingUIState: EldenRingUIState? = null) {
    val appNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(appNavController) }
    ) { padding ->
        NavHost(
            navController = appNavController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { Home(appNavController, eldenRingUIState) }
            composable("bookmarks") { SavedItems(appNavController) }
        }
    }
}
