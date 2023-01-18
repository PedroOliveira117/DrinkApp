package com.example.drinkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drinkapp.common.navigation.NavigationUtils.DRINK_DETAIL_SCREEN
import com.example.drinkapp.presentation.drinkdetail.composes.DrinkDetailScreen
import com.example.drinkapp.presentation.drinksearch.composes.DrinkSearchScreen
import com.example.drinkapp.presentation.drinkslist.bottomnavigation.BottomBar
import com.example.drinkapp.presentation.drinkslist.bottomnavigation.BottomBarScreen
import com.example.drinkapp.presentation.drinkslist.composes.DrinksListScreen
import com.example.drinkapp.ui.theme.DrinkAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinkAppTheme {
                val navController = rememberNavController()
                var displayBottomBar by rememberSaveable {
                    mutableStateOf(true)
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                displayBottomBar = navBackStackEntry?.destination?.route != "$DRINK_DETAIL_SCREEN/{drinkId}"

                Scaffold(
                    bottomBar = {
                        if (displayBottomBar) {
                            BottomBar(navController = navController)
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomBarScreen.Home.route,
                        modifier = Modifier.padding(padding)
                    ) {

                        composable(route = BottomBarScreen.Home.route) {
                            DrinksListScreen(navController)
                        }

                        composable(route = BottomBarScreen.Search.route) {
                            DrinkSearchScreen(navController)
                        }

                        composable(
                            route = "$DRINK_DETAIL_SCREEN/{drinkId}",
                            arguments = listOf(
                                navArgument("drinkId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            DrinkDetailScreen()
                        }
                    }
                }
            }
        }
    }
}