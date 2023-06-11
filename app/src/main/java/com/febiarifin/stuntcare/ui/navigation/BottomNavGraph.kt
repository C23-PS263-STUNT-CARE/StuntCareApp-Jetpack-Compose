package com.febiarifin.stuntcare.ui.navigation

import CheckScreen
import EducationScreen
import HomeScreen
import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.screen.auth.login.LoginScreen
import com.febiarifin.stuntcare.ui.screen.auth.register.RegisterScreen
import com.febiarifin.stuntcare.ui.screen.check.form.FormCheckScreen
import com.febiarifin.stuntcare.ui.screen.check.form.FormCopyCheckScreen
import com.febiarifin.stuntcare.ui.screen.check.form.FormUpdateCheckScreen
import com.febiarifin.stuntcare.ui.screen.detail.article.DetailArticleScreen
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckScreen
import com.febiarifin.stuntcare.util.UserPreference


@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)

    NavHost(
        navController = navController,
        startDestination = if (userPreference.getUserToken() != null) BottomBarScreen.Home.route else BottomBarScreen.Login.route
    ) {
        composable(route = BottomBarScreen.Login.route) {

            LoginScreen(
                navigateToRegister = {
                    navController.navigate(BottomBarScreen.Register.createRoute()) {
                        popUpTo(navController.graph.id) {
                            inclusive = false
                        }
                    }
                },
                navigateToHomeScreen = {
                    navController.navigate(BottomBarScreen.Home.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = false
                        }
                    }
                },
            )
        }
        composable(route = BottomBarScreen.Register.route) {
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(BottomBarScreen.Login.createRoute()) {
                        popUpTo(navController.graph.id) {
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
            ProfileScreen(
                navigateToCheck = {
                    navController.navigate(BottomBarScreen.Check.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToEducation = {
                    navController.navigate(BottomBarScreen.Education.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToInfo = {},
                navigateToLogin = {
                    navController.navigate(BottomBarScreen.Login.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = false
                        }
                    }
                },
            )
        }
        composable(
            route = BottomBarScreen.DetailCheck.route,
            arguments = listOf(navArgument("checkId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("checkId") ?: -1L
            DetailCheckScreen(
                checkId = id,
                navigateToBack = { navController.navigateUp() },
                navigateToUpdate = {checkId ->
                    navController.navigate(BottomBarScreen.UpdateCheck.createRoute(checkId)){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToCopy = {checkId ->
                    navController.navigate(BottomBarScreen.CopyCheck.createRoute(checkId)){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        composable(route = BottomBarScreen.FormCheck.route) {
            FormCheckScreen(
                navigateToBack = { navController.navigateUp() },
                navigateToCheck = {
                    navController.navigate(BottomBarScreen.Check.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToDetailCheck = { checkId ->
                    navController.navigate(BottomBarScreen.DetailCheck.createRoute(checkId)){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
        composable(
            route = BottomBarScreen.DetailArticle.route,
            arguments = listOf(navArgument("articleId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("articleId") ?: -1L
            DetailArticleScreen(
                articleId = id,
                navigateToBack = { navController.navigateUp() }
            )
        }
        composable(
            route = BottomBarScreen.UpdateCheck.route,
            arguments = listOf(navArgument("checkId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("checkId") ?: -1L

            FormUpdateCheckScreen(
                navigateToBack = { navController.navigateUp() },
                navigateToCheck = {
                    navController.navigate(BottomBarScreen.Check.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToDetailCheck = { checkId ->
                    navController.navigate(BottomBarScreen.DetailCheck.createRoute(checkId)){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                checkId = id,
            )
        }

        composable(
            route = BottomBarScreen.CopyCheck.route,
            arguments = listOf(navArgument("checkId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("checkId") ?: -1L

            FormCopyCheckScreen(
                navigateToBack = { navController.navigateUp() },
                navigateToCheck = {
                    navController.navigate(BottomBarScreen.Check.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToDetailCheck = { checkId ->
                    navController.navigate(BottomBarScreen.DetailCheck.createRoute(checkId)){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                checkId = id,
            )
        }
    }
}