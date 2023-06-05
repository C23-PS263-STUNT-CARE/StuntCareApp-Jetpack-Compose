package com.febiarifin.stuntcare.ui.screen.detail.check

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febiarifin.stuntcare.di.Injection
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.factory.CheckViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCheckScreen(
    checkId: Long,
    viewModel: DetailCheckViewModel = viewModel(
        factory = CheckViewModelFactory(
            Injection.provideCheckRepository()
        )
    ),
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Check Stunting",
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
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getCheckById(checkId)
                }

                is UiState.Success -> {
                    val data = uiState.data
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(80.dp))
                        DetailCheckRowItem(columnName = "Nama Anak", value = data.name)
                        DetailCheckRowItem(columnName = "Jenis Kelamin", value = data.sex)
                        DetailCheckRowItem(columnName = "Umur", value = data.age + " Bulan")
                        DetailCheckRowItem(
                            columnName = "Berat Lahir",
                            value = data.birth_weight + " Kg"
                        )
                        DetailCheckRowItem(
                            columnName = "Tinggi Lahir",
                            value = data.birth_length + " Cm"
                        )
                        DetailCheckRowItem(
                            columnName = "Berat Badan",
                            value = data.body_weight + " Kg"
                        )
                        DetailCheckRowItem(
                            columnName = "Tinggi Badan",
                            value = data.body_length + " Cm"
                        )
                        DetailCheckRowItem(columnName = "ASI Ekslusif", value = data.asi_ekslusif)
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun DetailCheckRowItem(
    columnName: String,
    value: String,
) {
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 0.dp)
            .border(
                1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray.copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            androidx.compose.material.Text(
                text = columnName,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true)
@Composable
fun DetailCheckScreePreview() {
    StuntCareTheme {
        DetailCheckScreen(
            1,
            navigateToBack = {},
        )
    }
}