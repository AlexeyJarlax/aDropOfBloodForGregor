package com.pavlovalexey.adropofbloodforgregor.nav

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.pavlovalexey.adropofbloodforgregor.screens.AboutScreen
import com.pavlovalexey.adropofbloodforgregor.screens.character.CharacterScreen
import com.pavlovalexey.adropofbloodforgregor.screens.InputScreen
import com.pavlovalexey.adropofbloodforgregor.screens.SettingsScreen
import com.pavlovalexey.adropofbloodforgregor.screens.story.StoryScreen
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel

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

    val gameViewModel: GameViewModel = hiltViewModel()

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
                    viewModel = gameViewModel,
                    onNavigateToStory = { char ->
                        gameViewModel.selectCharacter(char)
                        navController.navigate("story/$char")
                    }
                )
            }

            composable(
                route = NavDestinations.STORY,
                arguments = listOf(navArgument("character") {
                    type = NavType.StringType
                })
            ) {
                StoryScreen(
                    viewModel = gameViewModel,
                    onNavigateToCharacter = {
                        navController.popBackStack()  // вернуться на экран персонажей
                    },
                    onNavigateToKeyInput = {
                        navController.navigate(NavDestinations.SOME_INPUT)
                    }
                )
            }

            composable(NavDestinations.SOME_INPUT) {
                InputScreen(
                    viewModel = gameViewModel,
                    onExit = { navController.popBackStack() }
                )
            }

            composable(NavDestinations.SETTINGS) {
                SettingsScreen(
                    navController = navController,
                    viewModel = gameViewModel,
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