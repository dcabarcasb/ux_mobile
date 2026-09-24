package com.ejemplo.alarmafamiliar.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ejemplo.alarmafamiliar.data.NewAlarm
import com.ejemplo.alarmafamiliar.ui.components.AlarmaTabBar
import com.ejemplo.alarmafamiliar.ui.components.TAB_DESTINATIONS
import com.ejemplo.alarmafamiliar.ui.screens.AlarmsScreen
import com.ejemplo.alarmafamiliar.ui.screens.CreateEventScreen
import com.ejemplo.alarmafamiliar.ui.screens.CreateLocationScreen
import com.ejemplo.alarmafamiliar.ui.screens.CreateScheduleScreen
import com.ejemplo.alarmafamiliar.ui.screens.CreateSuccessScreen
import com.ejemplo.alarmafamiliar.ui.screens.FamilyScreen
import com.ejemplo.alarmafamiliar.ui.screens.HomeScreen
import com.ejemplo.alarmafamiliar.ui.screens.LoginScreen
import com.ejemplo.alarmafamiliar.ui.screens.MapScreen
import com.ejemplo.alarmafamiliar.ui.screens.OnboardingScreen
import com.ejemplo.alarmafamiliar.ui.screens.ProfileScreen
import com.ejemplo.alarmafamiliar.ui.screens.SosScreen
import com.ejemplo.alarmafamiliar.ui.screens.SosSentScreen

const val ROUTE_ONBOARDING = "onboarding"
const val ROUTE_LOGIN = "login"
const val ROUTE_INICIO = "inicio"
const val ROUTE_MAPA = "mapa"
const val ROUTE_ALARMAS = "alarmas"
const val ROUTE_FAMILIA = "familia"
const val ROUTE_PERFIL = "perfil"
const val ROUTE_CREATE = "create"
const val ROUTE_CREATE_SCHEDULE = "create/hora"
const val ROUTE_CREATE_LOCATION = "create/lugar"
const val ROUTE_CREATE_SUCCESS = "create/exito"
const val ROUTE_SOS = "sos"
const val ROUTE_SOS_SENT = "sos/enviado"

private val tabRoutes = setOf(
    ROUTE_INICIO, ROUTE_MAPA, ROUTE_ALARMAS, ROUTE_FAMILIA, ROUTE_PERFIL
)

@Composable
fun AlarmaNavHost(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in tabRoutes

    Scaffold(
        containerColor = Color(0xFFF6F9FB),
        bottomBar = {
            if (showBottomBar) {
                AlarmaTabBar(currentRoute = currentRoute) { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ROUTE_ONBOARDING,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ROUTE_ONBOARDING) {
                OnboardingScreen(onLogin = {
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_ONBOARDING) { inclusive = true }
                    }
                })
            }
            composable(ROUTE_LOGIN) {
                LoginScreen(
                    onLogin = {
                        navController.navigate(ROUTE_INICIO) {
                            popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onRegister = {
                        navController.navigate(ROUTE_INICIO) {
                            popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(ROUTE_INICIO) {
                HomeScreen(
                    onNavigateToAlarmas = { navController.navigateToTab(ROUTE_ALARMAS) },
                    onNavigateToMapa = { navController.navigateToTab(ROUTE_MAPA) },
                    onCreateAlarm = { navController.navigateToCreate() },
                    onSos = { navController.navigate(ROUTE_SOS) }
                )
            }
            composable(ROUTE_MAPA) { MapScreen() }
            composable(ROUTE_ALARMAS) {
                AlarmsScreen(onCreateAlarm = { navController.navigateToCreate() })
            }
            composable(ROUTE_FAMILIA) {
                FamilyScreen(onAddMember = { navController.navigate(ROUTE_FAMILIA) })
            }
            composable(ROUTE_PERFIL) {
                ProfileScreen(onLogout = {
                    navController.navigate(ROUTE_ONBOARDING) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                })
            }

            composable(ROUTE_CREATE) {
                CreateEventScreen(
                    onBack = { navController.popBackStack() },
                    onContinue = { navController.navigate(ROUTE_CREATE_SCHEDULE) }
                )
            }
            composable(ROUTE_CREATE_SCHEDULE) {
                CreateScheduleScreen(
                    onBack = { navController.popBackStack() },
                    onContinue = { navController.navigate(ROUTE_CREATE_LOCATION) }
                )
            }
            composable(ROUTE_CREATE_LOCATION) {
                CreateLocationScreen(
                    onBack = { navController.popBackStack() },
                    onContinue = { navController.navigate(ROUTE_CREATE_SUCCESS) }
                )
            }
            composable(ROUTE_CREATE_SUCCESS) {
                CreateSuccessScreen(
                    onViewAlarms = { navController.navigateToTab(ROUTE_ALARMAS, clearCreate = true) },
                    onBackHome = { navController.navigateToTab(ROUTE_INICIO, clearCreate = true) }
                )
            }

            composable(ROUTE_SOS) {
                SosScreen(
                    onSend = { navController.navigate(ROUTE_SOS_SENT) },
                    onCancel = { navController.popBackStack() }
                )
            }
            composable(ROUTE_SOS_SENT) {
                SosSentScreen(onBackHome = { navController.navigateToTab(ROUTE_INICIO, clearCreate = true) })
            }
        }
    }
}

private fun NavHostController.navigateToTab(route: String, clearCreate: Boolean = false) {
    NewAlarm.reset()
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = clearCreate
    }
}

private fun NavHostController.navigateToCreate() {
    NewAlarm.reset()
    navigate(ROUTE_CREATE) {
        launchSingleTop = true
    }
}