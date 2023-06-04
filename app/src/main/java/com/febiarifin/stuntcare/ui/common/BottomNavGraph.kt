package com.febiarifin.stuntcare.ui.common

import CheckScreen
import EducationScreen
import HomeScreen
import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Check.route){
            CheckScreen()
        }
        composable(route = BottomBarScreen.Education.route){
            EducationScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}