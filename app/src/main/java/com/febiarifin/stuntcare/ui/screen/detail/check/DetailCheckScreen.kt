package com.febiarifin.stuntcare.ui.screen.detail.check

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.di.Injection
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.factory.ViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCheckScreen(
    checkId: Long,
    viewModel: DetailCheckViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
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
                    DetailCheckContent(check = uiState.data)
                }

                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun DetailCheckContent(
    check: Check,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner_header),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CustomText(
                text = if (check.result >= 1) "Berisiko Stunting" else "Aman Dari Stunting",
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        CustomText(text = "Indentitas Anak", withPadding = false, isBold = true)
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            content = {
                Row {
                    CustomText(text = "Nama Anak", color = Color.Black.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.weight(1f))
                    CustomText(
                        text = check.name,
                        color = Color.Black.copy(alpha = 0.7f),
                        isBold = true
                    )
                }
                Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
                Row {
                    CustomText(text = "Jenis Kelamin", color = Color.Black.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.weight(1f))
                    CustomText(
                        text = if (check.sex == "M") "Laki-Laki" else "Perempuan",
                        color = Color.Black.copy(alpha = 0.7f),
                        isBold = true
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            text = "Indikator Tinggi Lahir (Kurang 1 Minggu)",
            isBold = true,
            withPadding = false
        )
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            content = {
                Row {
                    CustomText(text = "Tinggi Lahir Anak", color = Color.Black.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        modifier = Modifier
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            if (check.birth_length.toDouble() > 47 ){ Color.Green.copy(alpha = 0.5f)}
                            else if (check.birth_length.toDouble() >= 45 ){ Color.Yellow.copy(alpha = 0.5f)}
                            else{ Color.Red.copy(alpha = 0.5f)}
                        ),
                        content = {
                            CustomText(
                                text = check.birth_length + " CM",
                                color = Color.Black.copy(alpha = 0.7f),
                                isBold = true
                            )
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Green.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = "> 47 CM")
                }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Yellow.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = ">= 45 CM")
                }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Red.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = "< 45 CM")
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        CustomText(text = "Indikator Berat Lahir (Kurang 1 Minggu)", withPadding = false, isBold = true)
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            content = {
                Row {
                    CustomText(text = "Berat Lahir Anak", color = Color.Black.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        modifier = Modifier
                            .weight(1f),
                        colors = CardDefaults.cardColors(
                            if (check.birth_weight.toDouble() >= 3.3 ){ Color.Green.copy(alpha = 0.5f)}
                            else if (check.birth_weight.toDouble() >= 2.2 ){ Color.Yellow.copy(alpha = 0.5f)}
                            else{ Color.Red.copy(alpha = 0.5f)}
                        ),
                        content = {
                            CustomText(
                                text = check.birth_weight + " KG",
                                color = Color.Black.copy(alpha = 0.7f),
                                isBold = true
                            )
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Green.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = "3.3 KG")
                }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Yellow.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = "2.2 - 2.5 KG")
                }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Card(
                modifier = Modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(Color.Red.copy(alpha = 0.5f)),
                content = {
                    CustomText(text = "< 2.1 KG")
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomText(text = "Indikator Lainnya", withPadding = false, isBold = true)
        Spacer(modifier = Modifier.height(6.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            content = {
                Row {
                    CustomText(text = "Tinggi Badan Anak")
                    Spacer(modifier = Modifier.weight(1f))
                    CustomText(text = check.body_length + " CM", isBold = true)
                }
                Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
                Row {
                    CustomText(text = "Berat Badan Anak")
                    Spacer(modifier = Modifier.weight(1f))
                    CustomText(text = check.body_weight + " KG", isBold = true)
                }
                Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
                Row {
                    CustomText(text = "ASI Ekslusif")
                    Spacer(modifier = Modifier.weight(1f))
                    Card(
                        modifier = Modifier,
                        colors = CardDefaults.cardColors(
                            if (check.asi_ekslusif == "Yes") Color.Green.copy(
                                alpha = 0.5f
                            ) else Color.Red.copy(alpha = 0.5f)
                        ),
                        content = {
                            CustomText(text = if (check.asi_ekslusif == "Yes") "YA" else "TIDAK", isBold = true)
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun Card(
    content: @Composable () -> Unit,
    padding: Dp
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        content = {
            content
        }
    )
}

@Composable
fun CustomText(
    text: String,
    isBold: Boolean? = false,
    withPadding: Boolean? = true,
    color: Color? = Color.Black
) {
    Text(
        text,
        modifier = Modifier.padding(if (withPadding == true) 16.dp else 0.dp),
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center,
        fontWeight = if (isBold == true) FontWeight.Bold else FontWeight.Normal,
        color = color!!
    )
}

@Preview(showBackground = true)
@Composable
fun DetailCheckScreePreview() {
    StuntCareTheme {
        DetailCheckScreen(
            1,
            navigateToBack = {},
        )
//        DetailCheckContent(
//            Check(1, "Anak 1", "Laki-laki", "2", "1.5", "40", "4", "80", "Yes", 1.0),
//        )
    }
}