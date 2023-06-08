package com.febiarifin.stuntcare.ui.screen.check.form

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.ui.components.ShowSnackBar
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCheckScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
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
            CheckForm()
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}

@Composable
fun CheckForm() {
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
            label = { androidx.compose.material.Text("Umur Anak (Bulan)", fontSize = 16.sp) },
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
            label = { androidx.compose.material.Text("Berat Lahir Anak (Kg)", fontSize = 16.sp) },
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
            label = { androidx.compose.material.Text("Tinggi Lahir Anak (Cm)", fontSize = 16.sp) },
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
            label = { androidx.compose.material.Text("Berat Badan Anak(Kg)", fontSize = 16.sp) },
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
            label = { androidx.compose.material.Text("Tinggi Badan Anak(Cm)", fontSize = 16.sp) },
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
                if (name.isNotEmpty() && age.isNotEmpty() && birth_weight.isNotEmpty() && birth_length.isNotEmpty() && body_weight.isNotEmpty() && body_length.isNotEmpty() && selectedGender.isNotEmpty() && selectedASI.isNotEmpty()){
                    showError = false
                    isFormCheckCompleted = true
                }else{
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
        } else if(isFormCheckCompleted) {
            isFormCheckCompleted = false
            Log.d("TEST", "Success")
            ShowSnackBar(message = "Semua Field Terisi " + name + "JK "+selectedGender+" ASI"+ selectedASI)
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
            navigateToBack = {}
        )
    }
}