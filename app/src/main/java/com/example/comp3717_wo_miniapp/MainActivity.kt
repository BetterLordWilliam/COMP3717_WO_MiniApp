package com.example.comp3717_wo_miniapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.comp3717_wo_miniapp.composables.Home
import com.example.comp3717_wo_miniapp.composables.SavedItems
import com.example.comp3717_wo_miniapp.data.EldenRingDB
import com.example.comp3717_wo_miniapp.data.EldenRingDatabase
import com.example.comp3717_wo_miniapp.data.eldenRingHttpClient
import com.example.comp3717_wo_miniapp.data.repositories.ArmourRepository
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingItemRepository
import com.example.comp3717_wo_miniapp.data.repositories.IncantationRepository
import com.example.comp3717_wo_miniapp.data.repositories.ItemsRepository
import com.example.comp3717_wo_miniapp.data.repositories.ShieldRepository
import com.example.comp3717_wo_miniapp.data.repositories.SorceryRepository
import com.example.comp3717_wo_miniapp.data.repositories.TalismanRepository
import com.example.comp3717_wo_miniapp.data.repositories.WeaponRepository
import com.example.comp3717_wo_miniapp.states.EldenRingSavedViewModel
import com.example.comp3717_wo_miniapp.states.EldenRingViewModel

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
 * Possible EldenRing item types.
 */
enum class ItemType {
    WEAPON,
    ARMOUR,
    SHIELD,
    SORCERY,
    INCANTATION,
    TALISMAN,
    ITEM
}

/**
 * Composable to represent the applications navigation bar
 */
@Composable
fun BottomNavBar(navController: NavController) {

    // Define the navigation bar items
    val navItems = listOf(
        NavItem(Icons.Sharp.Home, "home"),
        NavItem(Icons.Sharp.Favorite, "bookmarks")
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

class MainActivity : ComponentActivity() {

    // Lazy declare EldenRing repositories
    // Maybe there is a better way of doing dependency injection...
    private val erDb            by lazy { EldenRingDB.getDatabase(applicationContext) }
    private val weaponRepo      by lazy { WeaponRepository(eldenRingHttpClient, erDb.statDao(), erDb.weaponDao()) }
    private val armourRepo      by lazy { ArmourRepository(eldenRingHttpClient, erDb.statDao(), erDb.armourDao()) }
    private val shieldRepo      by lazy { ShieldRepository(eldenRingHttpClient, erDb.statDao(), erDb.shieldDao()) }
    private val sorceryRepo     by lazy { SorceryRepository(eldenRingHttpClient, erDb.statDao(), erDb.sorceryDao()) }
    private val incantationRepo by lazy { IncantationRepository(eldenRingHttpClient, erDb.statDao(), erDb.incantationDao()) }
    private val talismanRepo    by lazy { TalismanRepository(eldenRingHttpClient, erDb.talismanDao()) }
    private val itemsRepo       by lazy { ItemsRepository(eldenRingHttpClient, erDb.itemDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val eldenRingViewModel = viewModel {
                EldenRingViewModel(
                    weaponRepo,
                    armourRepo,
                    shieldRepo,
                    sorceryRepo,
                    incantationRepo,
                    talismanRepo,
                    itemsRepo
                )
            }
            val eldenRingSavedViewModel = viewModel {
                EldenRingSavedViewModel(
                    weaponRepo,
                    armourRepo,
                    shieldRepo,
                    sorceryRepo,
                    incantationRepo,
                    talismanRepo,
                    itemsRepo
                )
            }

            val appNavController = rememberNavController()
            // Home(appNavController)

            Scaffold(
                bottomBar = { BottomNavBar(appNavController) }
            ) { padding ->
                NavHost(
                    navController = appNavController,
                    startDestination = "home",
                    modifier = Modifier.padding(padding),
                ) {
                    composable("home") {
                        Home()
                    }
                    composable("bookmarks") {
                        SavedItems()
                    }
                }
            }
        }
    }
}
