package com.febiarifin.stuntcare.ui.screen.detail.check

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.screen.check.form.FormCheckScreen
import com.febiarifin.stuntcare.util.UserPreference

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCheckScreen(
    checkId: Long,
    viewModel: DetailCheckViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
    navigateToUpdate: (Long) -> Unit,
    navigateToCopy: (Long) -> Unit,
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showConfirmDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail",
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
                },
                actions = {
                    IconButton(
                        onClick = {
                            navigateToCopy(checkId)
                        }
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy")
                    }
                    IconButton(
                        onClick = {
                            navigateToUpdate(checkId)
                        }
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(
                        onClick = {
                            showConfirmDeleteDialog = true
                        }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) {
        viewModel.getStuntingById(
            "Bearer " + userPreference.getUserToken().toString(),
            userPreference.getUserId().toString(),
            checkId.toInt()
        )
        DetailCheckContent(check = state.data?.data)
        if (state.data?.data == null) {
            ShowProgressBar(state = true, isFillMaxSize = true)
        }
        if (showConfirmDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    showConfirmDeleteDialog = false
                },
                title = {
                    Text(text = "Konfirmasi Hapus")
                },
                text = {
                    Text("Yakin ingin melanjutkan?")
                },
                confirmButton = {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape)
                            .background(Color.Green.copy(alpha = 0.2f))
                            .clickable(onClick = {
                                showConfirmDeleteDialog = false
                                viewModel.deleteStuntingById(
                                    "Bearer " + userPreference
                                        .getUserToken()
                                        .toString(),
                                    userPreference
                                        .getUserId()
                                        .toString(),
                                    checkId.toInt()
                                )
                                Toast
                                    .makeText(
                                        context,
                                        "Check Stunting Berhasil Dihapus",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                navigateToBack()
                            }),
                        contentAlignment = Alignment.Center,
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },
                dismissButton = {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape)
                            .background(Color.Gray.copy(alpha = 0.2f))
                            .clickable(onClick = {
                                showConfirmDeleteDialog = false
                            }),
                        contentAlignment = Alignment.Center,
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun DetailCheckContent(
    check: Check?,
) {
    if (check != null) {
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
                    text = if (check.status_stunting >= 1) "Berisiko Stunting" else "Aman Dari Stunting",
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
                        CustomText(
                            text = "Tinggi Lahir Anak",
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            modifier = Modifier
                                .weight(1f),
                            colors = CardDefaults.cardColors(
                                if (check.birth_length.toDouble() > 47) {
                                    Color.Green.copy(alpha = 0.5f)
                                } else if (check.birth_length.toDouble() >= 45) {
                                    Color.Yellow.copy(alpha = 0.5f)
                                } else {
                                    Color.Red.copy(alpha = 0.5f)
                                }
                            ),
                            content = {
                                CustomText(
                                    text = check.birth_length.toString() + " CM",
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
            CustomText(
                text = "Indikator Berat Lahir (Kurang 1 Minggu)",
                withPadding = false,
                isBold = true
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
                        CustomText(
                            text = "Berat Lahir Anak",
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            modifier = Modifier
                                .weight(1f),
                            colors = CardDefaults.cardColors(
                                if (check.birth_weight.toDouble() >= 3.3) {
                                    Color.Green.copy(alpha = 0.5f)
                                } else if (check.birth_weight.toDouble() >= 2.2) {
                                    Color.Yellow.copy(alpha = 0.5f)
                                } else {
                                    Color.Red.copy(alpha = 0.5f)
                                }
                            ),
                            content = {
                                CustomText(
                                    text = check.birth_weight.toString() + " KG",
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
                        CustomText(text = check.body_length.toString() + " CM", isBold = true)
                    }
                    Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
                    Row {
                        CustomText(text = "Berat Badan Anak")
                        Spacer(modifier = Modifier.weight(1f))
                        CustomText(text = check.body_weight.toString() + " KG", isBold = true)
                    }
                    Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.5f))
                    Row {
                        CustomText(text = "ASI Ekslusif")
                        Spacer(modifier = Modifier.weight(1f))
                        Card(
                            modifier = Modifier,
                            colors = CardDefaults.cardColors(
                                if (check.asi_eksklusif == "Yes") Color.Green.copy(
                                    alpha = 0.5f
                                ) else Color.Red.copy(alpha = 0.5f)
                            ),
                            content = {
                                CustomText(
                                    text = if (check.asi_eksklusif == "Yes") "YA" else "TIDAK",
                                    isBold = true
                                )
                            }
                        )
                    }
                }
            )
        }
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
//        DetailCheckScreen(
//            1,
//            navigateToBack = {},
//        )
        DetailCheckContent(
            Check(1, "Anak 1", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
        )
    }
}