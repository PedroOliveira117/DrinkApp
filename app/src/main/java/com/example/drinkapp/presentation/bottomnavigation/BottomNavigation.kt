package com.example.drinkapp.presentation.drinkslist.bottomnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drinkapp.common.navigation.NavigationUtils.DRINKS_LIST_SCREEN
import com.example.drinkapp.common.navigation.NavigationUtils.FAVORITE_DRINKS_SCREEN
import com.example.drinkapp.common.navigation.NavigationUtils.SEARCH_DRINKS_LIST_SCREEN
import com.example.drinkapp.ui.theme.*

/**
 * Created by pedrooliveira on 11/01/2023
 * All rights reserved GoodBarber
 */

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = DRINKS_LIST_SCREEN,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search : BottomBarScreen(
        route = SEARCH_DRINKS_LIST_SCREEN,
        title = "Search",
        icon = Icons.Default.Search
    )

    object Favorites : BottomBarScreen(
        route = FAVORITE_DRINKS_SCREEN,
        title = "Favorites",
        icon = Icons.Filled.Favorite
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Favorites
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(bottom = 10.dp, start = 30.dp, end = 30.dp),
    ) {
        BottomNavigation(
            modifier = modifier
                .clip(CircleShape),
            backgroundColor = gray_400
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = Color.Black,
        unselectedContentColor = gray_500,
        onClick = {
            if (currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } != true) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}