package com.example.drinkapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
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
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DrinkAppTheme {
                val navController = rememberNavController()
                var displayBottomBar by rememberSaveable {
                    mutableStateOf(true)
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                displayBottomBar = navBackStackEntry?.destination?.route != "$DRINK_DETAIL_SCREEN/{drinkId}"

                Scaffold(
                    modifier = Modifier.navigationBarsPadding(),
                    bottomBar = {
                        AnimatedVisibility(
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                            visible = displayBottomBar
                        ) {
                            BottomBar(navController = navController)
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomBarScreen.Home.route
                    ) {

                        composable(route = BottomBarScreen.Home.route) {
                            DrinksListScreen(
                                contentPadding = padding,
                                navController = navController
                            )
                        }

                        composable(route = BottomBarScreen.Favorites.route) {
                            DrinkSearchScreen(navController)
                        }

                        composable(route = BottomBarScreen.Search.route) {
                            DrinkSearchScreen(
                                contentPadding = padding,
                                navController = navController
                            )
                        }

                        composable(
                            route = "$DRINK_DETAIL_SCREEN/{drinkId}",
                            arguments = listOf(
                                navArgument("drinkId") {
                                    type = NavType.StringType
                                },
                            ),
                        ) {
                            DrinkDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}