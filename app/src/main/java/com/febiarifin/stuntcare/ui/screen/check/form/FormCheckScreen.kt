package com.febiarifin.stuntcare.ui.screen.check.form

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.components.ShowSnackBar
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.util.UserPreference

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCheckScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
    viewModel: FormCheckViewModel = hiltViewModel(),
    navigateToDetailCheck: (Long) -> Unit,
    navigateToCheck: () -> Unit,
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.state.collectAsStateWithLifecycle()

    val colorPrimary: Color = Color(0xFF3984E9)
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var birth_weight by remember { mutableStateOf("") }
    var birth_length by remember { mutableStateOf("") }
    var body_length by remember { mutableStateOf("") }
    var body_weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var selectedASI by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var isFormCheckCompleted by remember { mutableStateOf(false) }
    var showProgressBar by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var stuntingResult by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Form Check Stunting",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    )
                },
                modifier = Modifier.border(
                    1.dp,
                    color = Color.Gray.copy(alpha = 0.3f)
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navigateToBack() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = modifier.height(60.dp))
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { androidx.compose.material.Text("Nama Anak", fontSize = 16.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Umur Anak (Bulan)", fontSize = 16.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = birth_weight,
                    onValueChange = { birth_weight = it },
                    label = { Text("Berat Lahir Anak (Kg)", fontSize = 16.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = birth_length,
                    onValueChange = { birth_length = it },
                    label = {
                        androidx.compose.material.Text(
                            "Tinggi Lahir Anak (Cm)",
                            fontSize = 16.sp
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body_weight,
                    onValueChange = { body_weight = it },
                    label = { Text("Berat Badan Anak Saat Ini(Kg)", fontSize = 16.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body_length,
                    onValueChange = { body_length = it },
                    label = { Text("Tinggi Badan Anak Saat Ini(Cm)", fontSize = 16.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorPrimary,
                        focusedLabelColor = colorPrimary,
                        cursorColor = colorPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = "Pilih Jenis Kelamin", fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        RadioButtonOption(
                            text = "Laki-laki",
                            selected = selectedGender == "M",
                            onSelected = { selectedGender = "M" }
                        )
                        RadioButtonOption(
                            text = "Perempuan",
                            selected = selectedGender == "F",
                            onSelected = { selectedGender = "F" }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = "ASI Ekslusif", fontWeight = FontWeight.Bold)
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        RadioButtonOption(
                            text = "Ya",
                            selected = selectedASI == "Yes",
                            onSelected = { selectedASI = "Yes" }
                        )
                        RadioButtonOption(
                            text = "Tidak",
                            selected = selectedASI == "No",
                            onSelected = { selectedASI = "No" }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        if (name.isNotEmpty() && age.isNotEmpty() && birth_weight.isNotEmpty() && birth_length.isNotEmpty() && body_weight.isNotEmpty() && body_length.isNotEmpty() && selectedGender.isNotEmpty() && selectedASI.isNotEmpty()) {
                            showError = false
                            isFormCheckCompleted = true
                            viewModel.onEvent(
                                FormCheckEvent.OnCheckStunting(
                                    "Bearer " + userPreference.getUserToken().toString(),
                                    userPreference.getUserId().toString(),
                                    name,
                                    selectedGender,
                                    age.toInt(),
                                    birth_weight.toDouble(),
                                    birth_length.toDouble(),
                                    body_weight.toDouble(),
                                    body_length.toDouble(),
                                    selectedASI
                                )
                            )
                        } else {
                            showError = true
                            isFormCheckCompleted = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Cek Status Stunting",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (showError) {
                    ShowSnackBar(message = "Pastikan Semua Field Terisi")
                } else if (isFormCheckCompleted) {
                    isFormCheckCompleted = false
                    Log.d("TEST", "Success")
                }
            }

            LaunchedEffect(key1 = state.loading) {
                showProgressBar = state.loading
                if (state.data?.error == false) {
                    val data = state.data?.data
                    val result = data?.status_stunting
                    if (result != null) {
                        stuntingResult = result >= 1.0
                        showSuccessDialog = true
                    }
                }
                if (state.data?.error == true) {
                    Toast.makeText(context, state.data?.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    showSuccessDialog = true
                }
                state.errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
            ShowProgressBar(showProgressBar)
            Spacer(modifier = modifier.height(20.dp))

            if (showSuccessDialog && !showProgressBar) {
                name = ""
                age = ""
                birth_weight = ""
                birth_length = ""
                body_weight = ""
                body_length = ""
                selectedGender = ""
                selectedASI = ""

                AlertDialog(
                    onDismissRequest = {
                        showSuccessDialog = false
                        stuntingResult = false
                    },
                    title = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Hasil Check Stunting",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = if (stuntingResult) R.drawable.emoji_sad else R.drawable.emoji_happy),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = if (stuntingResult) "Beresiko Terkena Stunting" else "Aman Dari Stunting",
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    },
                    confirmButton = {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = CircleShape)
                                .background(Color.Green.copy(alpha = 0.2f))
                                .clickable(onClick = {
                                    showSuccessDialog = false
                                    stuntingResult = false
                                    navigateToDetailCheck(state.data?.data?.id!!.toLong())
                                }),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    },
                    dismissButton = {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .width(200.dp)
                                .clip(shape = CircleShape)
                                .background(Color.Blue.copy(alpha = 0.2f))
                                .clickable(onClick = {
                                    showSuccessDialog = false
                                    stuntingResult = false
                                    navigateToCheck()
                                }),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = "Lihat Riwayat Check Stunting")
                        }
                    },
                )

            }
        }
    }
}

@Composable
fun RadioButtonOption(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable(onClick = onSelected)
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun FormCheckScreeenPreview() {
    StuntCareTheme {
        FormCheckScreen(
            navigateToBack = {},
            navigateToDetailCheck = {},
            navigateToCheck = {},
        )
    }
}