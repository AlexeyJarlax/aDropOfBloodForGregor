package com.pavlovalexey.adropofbloodforgregor.nav

/** Павлов Алексей https://github.com/AlexeyJarlax */

import com.pavlovalexey.adropofbloodforgregor.R
import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red400

import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.ConfirmationDialog

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: IconType
)

sealed class IconType {
    data class VectorIcon(val imageVector: ImageVector) : IconType()
    data class ResourceIcon(val resourceId: Int) : IconType()
}

val colorStyle = Red400

@Composable
fun BottomNavigationBar(navController: NavHostController, activity: Activity) {
    var showExitDialog by remember { mutableStateOf(false) }

    val items = listOf(
        BottomNavItem(
            stringResource(id = R.string.character),
            NavDestinations.CHARACTER,
            icon = IconType.VectorIcon(Icons.Default.AccountCircle)
        ),
        BottomNavItem(
            stringResource(id = R.string.option),
            NavDestinations.SETTINGS,
            icon = IconType.VectorIcon(Icons.Default.Settings)
        ),
        BottomNavItem(
            stringResource(id = R.string.fAQ),
            NavDestinations.ABOUT,
            icon = IconType.VectorIcon(Icons.Default.Info)
        ),
        BottomNavItem(
            stringResource(id = R.string.exit),
            NavDestinations.EXIT,
            icon = IconType.ResourceIcon(R.drawable.door_open_30dp)
        )
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                icon = {
                    when (val iconType = item.icon) {
                        is IconType.VectorIcon -> Icon(
                            imageVector = iconType.imageVector,
                            contentDescription = null,
                            tint = colorStyle
                        )
                        is IconType.ResourceIcon -> Icon(
                            painter = painterResource(id = iconType.resourceId),
                            contentDescription = null,
                            tint = colorStyle
                        )
                    }
                },
                label = { Text(item.title, maxLines = 1, color = colorStyle) },
                selected = (currentRoute == item.route),
                onClick = {
                    when (item.route) {
                        NavDestinations.EXIT -> {
                            showExitDialog = true
                        }
                        NavDestinations.CHARACTER,
                        NavDestinations.SETTINGS,
                        NavDestinations.ABOUT -> {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        else -> {
                            navController.navigate(item.route)
                        }
                    }
                }
            )
        }
    }

    if (showExitDialog) {
        ConfirmationDialog(
            title = stringResource(id = R.string.exit),
            message = stringResource(id = R.string.confirm_exit_message),
            confirmButtonText = stringResource(id = R.string.yes),
            dismissButtonText = stringResource(id = R.string.no),
            onConfirm = {
                showExitDialog = false
                activity.finish()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }
}
