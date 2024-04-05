package dev.gregwilliams.geminiapp

import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gregwilliams.geminiapp.textexample.TextOnlyViewModel
import dev.gregwilliams.geminiapp.ui.TextAndImageScreen
import dev.gregwilliams.geminiapp.ui.TextOnlyScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    textOnlyViewModel: TextOnlyViewModel,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = MainDestinations.TEXT_ONLY_ROUTE,
    navActions: MainNavigationActions = remember(navController) {
        MainNavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(MainDestinations.TEXT_ONLY_ROUTE) {
            AppModalDrawer(
                drawerState = drawerState,
                currentRoute = currentRoute,
                navigationActions = navActions
            ) {
                TextOnlyScreen(
                    openDrawer = { coroutineScope.launch { drawerState.open() } },
                    viewModel = textOnlyViewModel
                )
            }
        }
        composable(MainDestinations.TEXT_AND_IMAGE_ROUTE) {
            AppModalDrawer(
                drawerState = drawerState,
                currentRoute = currentRoute,
                navigationActions = navActions
            ) {
                TextAndImageScreen (
                    openDrawer = { coroutineScope.launch { drawerState.open() } },
                )
            }
        }
    }
}