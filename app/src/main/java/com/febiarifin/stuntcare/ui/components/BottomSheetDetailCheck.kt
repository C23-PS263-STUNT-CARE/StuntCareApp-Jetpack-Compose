package com.febiarifin.stuntcare.ui.components

import CheckItem
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    check: Check,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            Column(
                modifier = modifier,
            ) {
                Row {
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(
                        onClick = { coroutineScope.launch { modalSheetState.hide() } }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = null,
                        )
                    }
                }
            }

            Column(
                modifier = modifier.padding(20.dp, 20.dp, 20.dp, 120.dp)
            ) {
                StuntingInfo(result = check.result)
                Spacer(modifier = modifier.height(30.dp))
                DetailRow(columnName = "Jenis Kelamin", value = check.sex)
                DetailRow(columnName = "Umur", value = check.age + " Tahun")
                DetailRow(columnName = "Berat Lahir", value = check.birth_weight + " Kg")
                DetailRow(columnName = "Tinggi Lahir", value = check.birth_length + " Cm")
                DetailRow(columnName = "Berat Badan", value = check.body_weight + " Kg")
                DetailRow(columnName = "Tinggi Badan", value = check.body_length + " Cm")
                DetailRow(columnName = "ASI Ekslusif", value = check.asi_ekslusif)
            }
        }
    ) {
        Scaffold {
            Box {
                CheckItem(
                    check,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DetailRow(
    columnName: String,
    value: String
) {
    Row {
        Text(
            text = columnName,
            fontSize = 14.sp,
            modifier = Modifier.width(130.dp)
        )
        Text(
            text = ":",
            fontSize = 14.sp,
            modifier = Modifier.width(10.dp)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun StuntingInfo(
    result: Double
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(
                    if (result >= 1) Color.Red.copy(alpha = 0.5f) else Color.Green.copy(
                        alpha = 0.5f
                    )
                )
                .clickable(onClick = {}),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (result >= 1) "Resiko Stunting" else "Aman Dari Stunting",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun BottomSheetLayoutPreview() {
    StuntCareTheme {
        BottomSheetLayout(
            Check(1, "Anak 1", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 1.0),
        )
    }
}