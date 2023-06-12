package com.febiarifin.stuntcare.ui.screen.auth.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.components.ShowSnackBar

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    viewModelRegister: RegisterViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModelRegister.state.collectAsStateWithLifecycle()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordConfirmationVisibility by remember { mutableStateOf(false) }
    val colorPrimary: Color = Color(0xFF3984E9)
    var showErrorEmail by remember { mutableStateOf(false) }
    var showErrorEmpty by remember { mutableStateOf(false) }
    var showErrorPasswordConfirmation by remember { mutableStateOf(false) }
    var showErrorPassword by remember { mutableStateOf(false) }
    var isRegisterFormComplete by remember { mutableStateOf(false) }
    var showProgressBar by remember { mutableStateOf(false) }
    var isRegisterFailed by remember { mutableStateOf(false) }
    var messageError by remember { mutableStateOf("") }

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
            text = "Yuk Daftar dan Akses Semua Fiturnya",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Lengkap") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorPrimary,
                focusedLabelColor = colorPrimary,
                cursorColor = colorPrimary,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorPrimary,
                focusedLabelColor = colorPrimary,
                cursorColor = colorPrimary,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it },
            label = { Text("Konfirmasi Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordConfirmationVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordConfirmationVisibility = !passwordConfirmationVisibility
                }) {
                    Icon(
                        imageVector = if (passwordConfirmationVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordConfirmationVisibility) "Hide Password" else "Show Password"
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
                val isPasswordValid = password.length >= 8
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == passwordConfirmation && isEmailValid && isPasswordValid) {
                    showErrorEmpty = false
                    showErrorEmail = false
                    showErrorPasswordConfirmation = false
                    showErrorPassword = false
                    isRegisterFormComplete = true
                    Log.d("TEST", "Form Register completed")
                    viewModelRegister.onEvent(RegisterEvent.OnSignUp(name, email, password, passwordConfirmation))
                } else if (name.isEmpty() && email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                    showErrorEmail = false
                    showErrorPasswordConfirmation = false
                    showErrorPassword = false
                    showErrorEmpty = true
                    isRegisterFormComplete = false
                } else if (!isEmailValid) {
                    showErrorEmpty = false
                    showErrorPasswordConfirmation = false
                    showErrorPassword = false
                    showErrorEmail = true
                    isRegisterFormComplete = false
                } else if (password != passwordConfirmation) {
                    showErrorEmpty = false
                    showErrorEmail = false
                    showErrorPassword = false
                    showErrorPasswordConfirmation = true
                    isRegisterFormComplete = false
                } else if (!isPasswordValid) {
                    showErrorEmpty = false
                    showErrorEmail = false
                    showErrorPasswordConfirmation = false
                    showErrorPassword = true
                    isRegisterFormComplete = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Daftar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                append("Sudah punya akun? ")
                withStyle(style = SpanStyle(color = colorPrimary)) {
                    append("Login")
                }
            },
            modifier = Modifier.clickable(
                onClick = {
                    navigateToLogin()
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
//        Divider(color = Color.Gray, thickness = 1.dp)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
//            shape = MaterialTheme.shapes.medium,
//            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.2f))
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.google),
//                    contentDescription = "Akun Google",
//                    modifier = Modifier
//                        .padding(end = 8.dp)
//                        .size(20.dp)
//                )
//                Text("Daftar dengan Akun Google")
//            }
//        }
        Spacer(modifier = Modifier.height(20.dp))

        if (showErrorEmpty) {
            ShowSnackBar(message = "Pastikan Semua Field Tidak Boleh Kosong")
        } else if (showErrorEmail) {
            ShowSnackBar(message = "Pastikan Input Email dengan Benar")
        } else if (showErrorPasswordConfirmation) {
            ShowSnackBar(message = "Pastikan Input Password Konfirmasi dengan Benar")
        } else if (showErrorPassword) {
            ShowSnackBar(message = "Pastikan Password Lebih dari 8 Karakter")
        } else if (isRegisterFormComplete) {
            isRegisterFormComplete = false
        }

        LaunchedEffect(key1 = state.loading){
            showProgressBar = state.loading
            if (state.data?.error == false) {
                navigateToLogin()
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
fun RegisterScreenPreview() {
    RegisterScreen(
        navigateToLogin = {}
    )
}

