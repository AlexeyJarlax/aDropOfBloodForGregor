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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: IconType
)

sealed class IconType {
    data class VectorIcon(val imageVector: ImageVector) : IconType()
    data class ResourceIcon(val resourceId: Int) : IconType()
}

/** Если нужно использовать ImageVector:

BottomNavItem(
title = "Info",
route = "info",
icon = IconType.VectorIcon(Icons.Default.Info)
)

Если нужно использовать ресурс:

BottomNavItem(
title = "Door",
route = "door",
icon = IconType.ResourceIcon(R.drawable.door_open_30dp)
)
 */

@Composable
fun BottomNavigationBar(navController: NavHostController, activity: Activity) {
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

        BottomNavItem(stringResource(id = R.string.fAQ), NavDestinations.ABOUT, icon = IconType.VectorIcon(Icons.Default.Info)),

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
                            contentDescription = null
                        )
                        is IconType.ResourceIcon -> Icon(
                            painter = painterResource(id = iconType.resourceId),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(item.title, maxLines = 1) },
                selected = currentRoute == item.route,
                onClick = {
                    if (item.route == NavDestinations.EXIT) {
                        activity.finish()
                    } else if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = item.route != NavDestinations.SETTINGS
                            popUpTo(navController.graph.startDestinationRoute ?: NavDestinations.CHARACTER) {
                                saveState = item.route != NavDestinations.SETTINGS
                            }
                        }
                    }
                }
            )
        }
    }
}