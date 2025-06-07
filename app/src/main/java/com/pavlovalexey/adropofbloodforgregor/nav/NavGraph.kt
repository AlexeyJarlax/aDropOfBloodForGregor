package com.pavlovalexey.adropofbloodforgregor.nav

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.pavlovalexey.adropofbloodforgregor.screens.AboutScreen
import com.pavlovalexey.adropofbloodforgregor.screens.CharacterScreen
import com.pavlovalexey.adropofbloodforgregor.screens.InputScreen
import com.pavlovalexey.adropofbloodforgregor.screens.SettingsScreen
import com.pavlovalexey.adropofbloodforgregor.screens.StoryScreen

object NavDestinations {
    const val CHARACTER = "character"
    const val STORY = "story/{character}"
    const val STORY_BASE = "story"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val SOME_INPUT = "some_input"
    const val EXIT = "exit"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    activity: Activity,
    modifier: Modifier = Modifier,
    intent: Intent?
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
                CharacterScreen { chosenChar ->
                    navController.navigate("story/$chosenChar")
                }
            }

            composable(
                route = NavDestinations.STORY,
                arguments = listOf(navArgument("character") { type = NavType.StringType })
            ) { backStackEntry ->
                StoryScreen(
                    onNavigateToCharacter = {
                        navController.navigate(NavDestinations.CHARACTER) {
                            popUpTo(NavDestinations.STORY_BASE) { inclusive = true }
                        }
                    },
                    onNavigateToKeyInput = {
                        navController.navigate(NavDestinations.SOME_INPUT)
                    }
                )
            }

            composable(NavDestinations.SOME_INPUT) {
                InputScreen()
            }
            composable(NavDestinations.SETTINGS) {
                SettingsScreen(
                    navController = navController,
                    onAboutClicked = { navController.navigate(NavDestinations.ABOUT) },
                    onSecuritySettingsClicked = { navController.navigate(NavDestinations.STORY_BASE) }
                )
            }
            composable(NavDestinations.ABOUT) {
                AboutScreen(navController)
            }
        }
    }
}

private fun shouldShowBottomBar(navController: NavHostController): Boolean {
    return true
}