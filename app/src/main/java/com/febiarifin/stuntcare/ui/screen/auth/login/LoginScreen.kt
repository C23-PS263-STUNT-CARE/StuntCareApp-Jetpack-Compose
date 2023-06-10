package com.febiarifin.stuntcare.ui.screen.auth.login

import StuntCareLightTheme
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.data.remote.retrofit.ApiConfig
import com.febiarifin.stuntcare.model.User
import com.febiarifin.stuntcare.data.remote.request.LoginRequest
import com.febiarifin.stuntcare.data.remote.response.LoginResponse
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.components.ShowSnackBar
import com.febiarifin.stuntcare.ui.screen.auth.GoogleAuthUiClient
import com.febiarifin.stuntcare.ui.screen.auth.SignInViewModel
import com.febiarifin.stuntcare.util.UserPreference
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

private val colorPrimary: Color = Color(0xFF3984E9)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    viewModelLogin: LoginViewModel = hiltViewModel()
) {
    val state by viewModelLogin.state.collectAsStateWithLifecycle()
    val signInWithGoogleViewModel = viewModel<SignInViewModel>()
    val stateSignInWithGoogle by signInWithGoogleViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val user = User()
    val scope = rememberCoroutineScope()
    val signInClient = remember {
        GoogleAuthUiClient(
            context.applicationContext,
            Identity.getSignInClient(context.applicationContext)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    val signInResult = signInClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    signInWithGoogleViewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    var passwordVisibility by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorEmail by remember { mutableStateOf(false) }
    var showErrorEmpty by remember { mutableStateOf(false) }
    var isLoginFormComplete by remember { mutableStateOf(false) }
    var showProgressBar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "StuntCare",
            color = colorPrimary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = "Yuk Login dan Akses Semua Fiturnya",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", fontSize = 16.sp) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorPrimary,
                focusedLabelColor = colorPrimary,
                cursorColor = colorPrimary,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", fontSize = 16.sp) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisibility) "Hide Password" else "Show Password"
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorPrimary,
                focusedLabelColor = colorPrimary,
                cursorColor = colorPrimary,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                if (email.isNotEmpty() && isEmailValid && password.isNotEmpty()) {
                    showErrorEmail = false
                    showErrorEmpty = false
                    isLoginFormComplete = true
                    Log.d("login", "LoginScreen: ")
                    viewModelLogin.onEvent(LoginEvent.OnSignIn(email, password))
                } else if (email.isEmpty() || password.isEmpty()) {
                    showErrorEmpty = true
                    isLoginFormComplete = false
                } else if (!isEmailValid) {
                    showErrorEmail = true
                    isLoginFormComplete = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Login",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                append("Belum punya akun? ")
                withStyle(style = SpanStyle(color = colorPrimary)) {
                    append("Daftar")
                }
            },
            modifier = Modifier.clickable(
                onClick = {
                    navigateToRegister()
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    val signInIntentSender = signInClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.2f))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Akun Google",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(20.dp)
                )
                Text("Login dengan Akun Google")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (showErrorEmail) {
            ShowSnackBar(message = "Pastikan Input Email dengan Benar")
        } else if (showErrorEmpty) {
            ShowSnackBar(message = "Email dan Password Tidak Boleh Kosong")
        } else if (isLoginFormComplete) {
            isLoginFormComplete = false
        }

        LaunchedEffect(key1 = stateSignInWithGoogle.isSignInSuccessful) {
            if (stateSignInWithGoogle.isSignInSuccessful) {
                Toast.makeText(context, "Sign In Susccesfully", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(key1 = state.loading) {
            showProgressBar = state.loading
            if (state.data?.error == false) {
                val data = state.data?.data
                Log.d("TEST", "${state.data?.message}")
                user.id = data?.id ?: ""
                user.name = data?.name ?: ""
                user.email = data?.email ?: ""
                user.token = data?.token ?: ""
                userPreference.setUser(user)
                navigateToHomeScreen()
            }
            if (state.data?.error == true) {
                Toast.makeText(context, state.data?.message.toString(), Toast.LENGTH_SHORT).show()
            }
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        ShowProgressBar(showProgressBar)
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    StuntCareLightTheme {
        LoginScreen(
            navigateToRegister = {},
            navigateToHomeScreen = {},
        )
    }
}


