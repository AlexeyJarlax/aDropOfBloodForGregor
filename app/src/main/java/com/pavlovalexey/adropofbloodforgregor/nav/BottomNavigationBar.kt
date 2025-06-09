package com.pavlovalexey.adropofbloodforgregor.nav

import android.app.Activity
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red400
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Transparent
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ConfirmationDialog
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: IconType
)

sealed class IconType {
    data class VectorIcon(val imageVector: androidx.compose.ui.graphics.vector.ImageVector) : IconType()
    data class ResourceIcon(val resourceId: Int) : IconType()
}

private val colorStyle = text2

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    activity: Activity
) {
    var showExitDialog by remember { mutableStateOf(false) }

    val items = listOf(
        BottomNavItem(
            stringResource(R.string.character),
            NavDestinations.CHARACTER,
            icon = IconType.VectorIcon(Icons.Default.AccountCircle)
        ),
        BottomNavItem(
            stringResource(R.string.option),
            NavDestinations.SETTINGS,
            icon = IconType.VectorIcon(Icons.Default.Settings)
        ),
        BottomNavItem(
            stringResource(R.string.fAQ),
            NavDestinations.ABOUT,
            icon = IconType.VectorIcon(Icons.Default.Info)
        ),
        BottomNavItem(
            stringResource(R.string.exit),
            NavDestinations.EXIT,
            icon = IconType.ResourceIcon(R.drawable.door_open_30dp)
        )
    )

    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    NavigationBar(
        modifier = Modifier.heightIn(max = 80.dp),
        containerColor = Transparent
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    when (val ic = item.icon) {
                        is IconType.VectorIcon ->
                            Icon(ic.imageVector, contentDescription = null, tint = colorStyle)
                        is IconType.ResourceIcon ->
                            Icon(
                                painter = painterResource(ic.resourceId),
                                contentDescription = null,
                                tint = colorStyle
                            )
                    }
                },
                // label = { Text(item.title, color = colorStyle) },
                selected = currentRoute == item.route,
                onClick = {
                    when (item.route) {
                        NavDestinations.EXIT -> showExitDialog = true
                        NavDestinations.CHARACTER -> {
                            navController.popBackStack(
                                route = NavDestinations.CHARACTER,
                                inclusive = false
                            )
                        }
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
                        else -> navController.navigate(item.route)
                    }
                }
            )
        }
    }

    if (showExitDialog) {
        ConfirmationDialog(
            title = stringResource(R.string.exit),
            message = stringResource(R.string.confirm_exit_message),
            confirmButtonText = stringResource(R.string.yes),
            dismissButtonText = stringResource(R.string.no),
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
