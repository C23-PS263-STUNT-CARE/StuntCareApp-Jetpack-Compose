package com.febiarifin.stuntcare.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector ? = null,
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Dashboard
    )

    object Check: BottomBarScreen(
        route = "check",
        title = "Check",
        icon = Icons.Default.Check
    )

    object Education: BottomBarScreen(
        route = "education",
        title = "Education",
        icon = Icons.Default.List
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object DetailCheck: BottomBarScreen("check/{checkId}"){
        fun createRoute(checkId: Long) = "check/$checkId"
    }

    object UpdateCheck: BottomBarScreen("update/{checkId}"){
        fun createRoute(checkId: Long) = "update/$checkId"
    }

    object CopyCheck: BottomBarScreen("copy/{checkId}"){
        fun createRoute(checkId: Long) = "copy/$checkId"
    }

    object FormCheck: BottomBarScreen("formcheck"){
        fun createRoute() = "formcheck"
    }

    object Login: BottomBarScreen("login"){
        fun createRoute() = "login"
    }

    object Register: BottomBarScreen("register"){
        fun createRoute() = "register"
    }

    object DetailArticle: BottomBarScreen("article/{articleId}"){
        fun createRoute(articleId: Long) = "article/$articleId"
    }

    object Info: BottomBarScreen("info"){
        fun createRoute() = "info"
    }
    object Welcome: BottomBarScreen("welcome")
}
