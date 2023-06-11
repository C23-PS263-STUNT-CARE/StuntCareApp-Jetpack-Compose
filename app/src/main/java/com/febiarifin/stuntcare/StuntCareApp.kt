import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.febiarifin.stuntcare.ui.common.BackPress
import com.febiarifin.stuntcare.ui.navigation.BottomBarScreen
import com.febiarifin.stuntcare.ui.navigation.BottomNavGraph
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StuntCareApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var showContent by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (currentRoute != BottomBarScreen.Login.route && currentRoute != BottomBarScreen.Register.route && currentRoute != BottomBarScreen.FormCheck.route && currentRoute != BottomBarScreen.DetailCheck.route && currentRoute != BottomBarScreen.DetailArticle.route && currentRoute != BottomBarScreen.UpdateCheck.route && currentRoute != BottomBarScreen.CopyCheck.route) {
                LaunchedEffect(Unit) {
                    delay(500)
                    showContent = true
                }
                if (showContent) {
                    BottomBar(navController = navController)
                }
            }
        }
    ) {
        BottomNavGraph(navController = navController)
    }

    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        showToast= false
    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        if (currentRoute == BottomBarScreen.Home.route || currentRoute == BottomBarScreen.Login.route || currentRoute == BottomBarScreen.Register.route) {
            backPressState = BackPress.InitialTouch
            showToast = true
        }else {
            navController.popBackStack()
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Check,
        BottomBarScreen.Education,
        BottomBarScreen.Profile
    )

    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(screen.title!!)
        },
        icon = {
            Icon(imageVector = screen.icon!!, contentDescription = "Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route){
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = androidx.compose.material3.NavigationBarItemDefaults
            .colors(
                selectedIconColor = MaterialTheme.colorScheme.secondary,
                indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    LocalAbsoluteTonalElevation.current
                ),
                selectedTextColor = MaterialTheme.colorScheme.secondary,
            )
    )
}

@Composable
fun StuntCareLightTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF459FEB),
            onPrimary = Color.White,
            secondary = Color(0xFF3984E9),
            onSecondary = Color.White,
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun StuntCareAppPreview() {
    StuntCareLightTheme {
        StuntCareApp()
    }
}