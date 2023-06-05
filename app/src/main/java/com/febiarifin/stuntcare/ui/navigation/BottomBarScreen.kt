package com.febiarifin.stuntcare.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
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
        icon = Icons.Default.Home
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

    object FormCheck: BottomBarScreen("formcheck"){
        fun createRoute() = "formcheck"
    }
}
