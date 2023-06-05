package com.febiarifin.stuntcare.ui.screen.check.form

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.screen.detail.check.DetailCheckRowItem
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import java.util.Objects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCheckScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    var selectedGender by remember { mutableStateOf("") }
    var selectedASI by remember { mutableStateOf("") }

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
            Spacer(modifier = modifier.height(80.dp))
            TextFieldWithValidation("Nama Anak", true)
            TextFieldWithValidation("Umur Anak (Bulan)", false)
            TextFieldWithValidation("Berat Lahir (Kg)", false)
            TextFieldWithValidation("Tinggi Lahir (Cm)", false)
            TextFieldWithValidation("Berat Badan (Kg)", false)
            TextFieldWithValidation("Tinggi Badan (Cm)", false)

            RadioButton(
                "Pilih Jenis Kelamin",
                {
                    RadioButtonOption(
                        text = "Laki-Laki",
                        selected = selectedGender == "M",
                        onSelected = { selectedGender = "M" }
                    )
                    RadioButtonOption(
                        text = "Perempuan",
                        selected = selectedGender == "F",
                        onSelected = { selectedGender = "F" }
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            RadioButton(
                "ASI Ekslusif",
                {
                    RadioButtonOption(
                        text = "YA",
                        selected = selectedASI == "Yes",
                        onSelected = { selectedASI = "Yes" }
                    )
                    RadioButtonOption(
                        text = "TIDAK",
                        selected = selectedASI == "No",
                        onSelected = { selectedASI = "No" }
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProgressButton()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithValidation(
    label: String,
    isKeyboardTypeText: Boolean,
) {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue
                isError = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White)
                .border(
                    width = 1.dp,
                    color = if (isError) Color.Red else Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                ),
            textStyle = TextStyle(color = Color.Black),
            label = {
                Text(text = label)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isKeyboardTypeText) KeyboardType.Text else KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isBlank()) {
                        isError = true
                    }
                }
            ),
            isError = isError
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun RadioButton(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            content()
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

@Composable
fun ProgressButton() {
    var isLoading by remember { mutableStateOf(false) }
    val content = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    isLoading = false
                }, 5000)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors =  ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cek Status Stunting",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
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