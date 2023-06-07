package com.febiarifin.stuntcare.ui.screen.auth.login

import StuntCareLightTheme
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.febiarifin.stuntcare.api.ApiConfig
import com.febiarifin.stuntcare.model.request.LoginRequest
import com.febiarifin.stuntcare.model.response.LoginResponse
import com.febiarifin.stuntcare.ui.components.ShowSnackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var apiConfig: ApiConfig
private val colorPrimary: Color = Color(0xFF3984E9)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorEmail by remember { mutableStateOf(false) }
    var showErrorEmpty by remember { mutableStateOf(false) }
    var isLoginFormComplete by remember { mutableStateOf(false) }
    var showProgressBar by remember { mutableStateOf(false) }
    var isLoginFailed by remember { mutableStateOf(false) }

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
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                if (email.isNotEmpty() && isEmailValid && password.isNotEmpty()) {
                    showErrorEmail = false
                    showErrorEmpty = false
                    isLoginFormComplete = false
                    isLoginFormComplete = true
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

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Akun Google",
                    modifier = Modifier.padding(end = 8.dp)
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
            showProgressBar = true
            apiConfig = ApiConfig()
            apiConfig.getApiService().loginUser(LoginRequest(email, password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        showProgressBar = false
                    }
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TEST", response.body()?.results?.data.toString())
                            navigateToRegister()
                        }else{
                            isLoginFailed = true
                        }
                    }
                })
        }

        if (isLoginFailed){
            ShowSnackBar(message = "Email dan Password salah. Pastikan input dengan benar")
            showProgressBar = false
        }
        ShowProgressbar(showProgressBar)
    }
}

@Composable
fun ShowProgressbar(
    state: Boolean
) {
    if (state){
        CircularProgressIndicator(
            color = colorPrimary,
            strokeWidth = 4.dp,
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    StuntCareLightTheme {
        LoginScreen(
            navigateToRegister = {},
        )
    }
}
