package com.example.drinkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinkapp.presentation.beerslist.bottomnavigation.BottomBar
import com.example.drinkapp.presentation.beerslist.bottomnavigation.BottomBarScreen
import com.example.drinkapp.presentation.beerslist.composes.BeersListScreen
import com.example.drinkapp.ui.theme.DrinkAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinkAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    }) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomBarScreen.Home.route,
                        modifier = Modifier.padding(padding)
                    ) {

                        composable(route = BottomBarScreen.Home.route) {
                            BeersListScreen()
                        }

                        composable(route = BottomBarScreen.Search.route) {
                            // TODO Addes Search Screen
                        }
                    }
                }
            }
        }
    }
}