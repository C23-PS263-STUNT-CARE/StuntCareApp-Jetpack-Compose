package com.febiarifin.stuntcare.ui.navigation

import CheckScreen
import EducationScreen
import HomeScreen
import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.febiarifin.stuntcare.ui.components.BottomSheetProfileLayout
import com.febiarifin.stuntcare.ui.screen.check.form.FormCheckScreen
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToFormCheck = {
                    navController.navigate(BottomBarScreen.FormCheck.createRoute())
                }
            )
        }
        composable(route = BottomBarScreen.Check.route) {
            CheckScreen(
                navigateToDetailCheck = { checkId ->
                    navController.navigate(BottomBarScreen.DetailCheck.createRoute(checkId))
                },
                navigateToFormCheck = {
                    navController.navigate(BottomBarScreen.FormCheck.createRoute())
                }
            )
        }
        composable(route = BottomBarScreen.Education.route) {
            EducationScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(
            route = BottomBarScreen.DetailCheck.route,
            arguments = listOf(navArgument("checkId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("checkId") ?: -1L
            DetailCheckScreen(
                checkId = id,
                navigateToBack = { navController.navigateUp() }
            )
        }
        composable(route = BottomBarScreen.FormCheck.route) {
            FormCheckScreen(
                navigateToBack = { navController.navigateUp() }
            )
        }
    }
}