package com.pavlovalexey.adropofbloodforgregor.nav

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pavlovalexey.adropofbloodforgregor.screens.AboutScreen
import com.pavlovalexey.adropofbloodforgregor.screens.CharacterScreen
import com.pavlovalexey.adropofbloodforgregor.screens.InputScreen
import com.pavlovalexey.adropofbloodforgregor.screens.SettingsScreen
import com.pavlovalexey.adropofbloodforgregor.screens.StoryScreen

object NavDestinations {
    const val CHARACTER = "character" // выбор персонажа
    const val ABOUT = "about"
    const val SETTINGS = "settings"
    const val STORY = "story"
    const val SOME_INPUT = "some_input"
    const val EXIT = "exit"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    activity: Activity,
    modifier: Modifier = Modifier,
    intent: Intent?,
) {

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(navController)) {
                BottomNavigationBar(navController, activity)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.CHARACTER,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavDestinations.CHARACTER) {
                CharacterScreen(
                    onNavigateToStory = { navController.navigate(NavDestinations.STORY) }
                )
            }

            composable(NavDestinations.STORY) {
                StoryScreen(
                    onNavigateToCharacter = {
                        navController.navigate(NavDestinations.CHARACTER) {
                            popUpTo(NavDestinations.STORY) { inclusive = true }
                        }
                    },
                    onNavigateToKeyInput = { navController.navigate(NavDestinations.SOME_INPUT) }
                )
            }
            composable(NavDestinations.SOME_INPUT) {
                InputScreen()
            }


            composable(NavDestinations.SETTINGS) {
                SettingsScreen(
                    navController = navController,
                    onAboutClicked = { navController.navigate(NavDestinations.ABOUT) },
                    onSecuritySettingsClicked = { navController.navigate(NavDestinations.STORY) }
                )
            }
            composable(NavDestinations.ABOUT) {
                AboutScreen(navController)
            }
        }
    }
}

private fun shouldShowBottomBar(navController: NavHostController): Boolean {
//    val currentDestination = navController.currentBackStackEntry?.destination?.route
//    return when (currentDestination) {
//        NavDestinations.CHARACTER, NavDestinations.SOME_INPUT, NavDestinations.SET_PASSWORD -> false
//        else -> true
//    }
    return true
}