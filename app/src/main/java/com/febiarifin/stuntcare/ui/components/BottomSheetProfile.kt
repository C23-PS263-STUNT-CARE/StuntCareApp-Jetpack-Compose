package com.febiarifin.stuntcare.ui.components

import CheckItem
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
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
fun BottomSheetProfileLayout(
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
                Spacer(modifier = modifier.height(30.dp))
                DetailProfileRow(columnName = "Nama", value = "Febi Arifin", Icons.Default.Person)
                DetailProfileRow(
                    columnName = "Email",
                    value = "febiarifin@email.com",
                    Icons.Default.Email
                )
                Spacer(modifier = modifier.height(30.dp))
                LogoutButton()
            }
        }
    ) {
        Scaffold {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .clickable(onClick = {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }),
                contentAlignment = androidx.compose.ui.Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

//            Column(
//                Modifier.fillMaxHeight(),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Row{
//                    Spacer(modifier = modifier.weight(1f))
//                    Box(
//                        modifier = Modifier
//                            .size(40.dp)
//                            .clip(shape = CircleShape)
//                            .background(Color.Gray.copy(alpha = 0.2f))
//                            .clickable(onClick = {
//                                coroutineScope.launch {
//                                    if (modalSheetState.isVisible)
//                                        modalSheetState.hide()
//                                    else
//                                        modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
//                                }
//                            }),
//                        contentAlignment = androidx.compose.ui.Alignment.Center,
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Person,
//                            contentDescription = null,
//                            tint = Color.Black
//                        )
//                    }
//                }
//            }
        }
    }
}

@Composable
fun DetailProfileRow(
    columnName: String,
    value: String,
    icon: ImageVector,
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
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = columnName,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun LogoutButton() {
    Card(
        modifier = Modifier
            .clickable {

            }
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 0.dp)
            .border(
                1.dp,
                shape = RoundedCornerShape(8.dp),
                color = Color.Gray.copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red,
        ),
    ) {
        Row(
            modifier = Modifier.padding(100.dp, 20.dp, 100.dp, 20.dp)
        ) {
            Text(
                text = "Logout",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.White)
        }
    }
}

@Preview
@Composable
fun BottomSheetLayoutPreview() {
    StuntCareTheme {
        BottomSheetProfileLayout()
    }
}