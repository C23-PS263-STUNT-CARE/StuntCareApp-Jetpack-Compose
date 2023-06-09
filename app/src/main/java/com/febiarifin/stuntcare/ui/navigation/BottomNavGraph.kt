package com.febiarifin.stuntcare.ui.navigation

import CheckScreen
import EducationScreen
import HomeScreen
import ProfileScreen
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.febiarifin.stuntcare.ui.screen.auth.SignInViewModel
import com.febiarifin.stuntcare.ui.screen.auth.login.LoginScreen
import com.febiarifin.stuntcare.ui.screen.auth.register.RegisterScreen
import com.febiarifin.stuntcare.ui.screen.check.form.FormCheckScreen
import com.febiarifin.stuntcare.ui.screen.detail.article.DetailArticleScreen
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckScreen
import com.febiarifin.stuntcare.util.UserPreference
import androidx.lifecycle.lifecycleScope
import com.febiarifin.stuntcare.ui.screen.auth.GoogleAuthUiClient
import kotlinx.coroutines.coroutineScope


@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)

//    val googleAuthUiClient by lazy {
//        GoogleAuthUiClient(
//            context = context,
//            oneTapClient = Identity.getSignInClient()
//        )
//    }

    NavHost(
        navController = navController,
        startDestination = if (userPreference.getUserToken() != null) BottomBarScreen.Home.route else BottomBarScreen.Login.route
    ) {
        composable(route = BottomBarScreen.Login.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        val data: Intent? = result.data
//                        lifecycleScope.launch {
//                            val signInResult = googleAuthUiClient.signInWithIntent(
//                                intent = result.data ?: return@launch
//                            )
//                            viewModel.onSignInResult(signInResult)
//                        }
                    }
                }
            )
            
//            LaunchedEffect(key1 = state.isSignInSuccessful){
//                coroutineScope {  }
//            }
            
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
            arguments = listOf(navArgument("articleId") { type = NavType.LongType }),
        ) {
            val id = it.arguments?.getLong("articleId") ?: -1L
            DetailArticleScreen(
                articleId = id,
                navigateToBack = { navController.navigateUp() }
            )
        }
    }
}