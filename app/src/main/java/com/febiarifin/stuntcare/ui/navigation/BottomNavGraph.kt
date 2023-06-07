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
import com.febiarifin.stuntcare.api.ApiService
import com.febiarifin.stuntcare.ui.screen.auth.login.LoginScreen
import com.febiarifin.stuntcare.ui.screen.auth.register.RegisterScreen
import com.febiarifin.stuntcare.ui.screen.check.form.FormCheckScreen
import com.febiarifin.stuntcare.ui.screen.detail.article.DetailArticleScreen
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val state = false
    NavHost(navController = navController, startDestination = if (state) BottomBarScreen.Home.route else BottomBarScreen.Login.route) {
        composable(route = BottomBarScreen.Login.route){
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(BottomBarScreen.Register.createRoute()){
                        popUpTo(navController.graph.id){
                            inclusive = false
                        }
                    }
                }
            )
        }
        composable(route = BottomBarScreen.Register.route){
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(BottomBarScreen.Login.createRoute()){
                        popUpTo(navController.graph.id){
                            inclusive = false
                        }
                    }
                }
            )
        }
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
            EducationScreen(
                navigateToDetailArticle = { articleId ->
                    navController.navigate(BottomBarScreen.DetailArticle.createRoute(articleId))
                }
            )
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
        composable(
            route = BottomBarScreen.DetailArticle.route,
            arguments = listOf(navArgument("articleId"){ type = NavType.LongType }),
        ){
            val id = it.arguments?.getLong("articleId") ?: -1L
            DetailArticleScreen(
                articleId = id,
                navigateToBack = { navController.navigateUp()}
            )
        }
    }
}