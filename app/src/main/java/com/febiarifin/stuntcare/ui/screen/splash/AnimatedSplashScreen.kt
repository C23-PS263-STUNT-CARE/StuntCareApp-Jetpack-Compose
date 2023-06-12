package com.febiarifin.stuntcare.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.ui.navigation.BottomBarScreen
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.util.UserPreference
import kotlinx.coroutines.delay

private val colorPrimary: Color = Color(0xFF0FE4ED)

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)

    var startAnimation by remember { mutableStateOf(false) }
    var alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(1100)
        navController.popBackStack()
        if(userPreference.getSplashState()){
            navController.navigate(BottomBarScreen.Welcome.route)
        }else if(userPreference.getUserToken() != null){
            navController.navigate(BottomBarScreen.Home.route)
        }else{
            navController.navigate(BottomBarScreen.Login.route)
        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
                ){
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .alpha(alpha)
            )
            Text(
                text = "STUNCARE",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorPrimary,
                modifier = Modifier
                    .alpha(alpha)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    StuntCareTheme {
        Splash(alpha = 1f)
    }
}