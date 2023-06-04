package com.febiarifin.stuntcare.ui.screen.detail.check

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCheckScreen(
    modifier: Modifier = Modifier
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
                        onClick = {}
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) {
        Column {
            Spacer(modifier = modifier.height(80.dp))
            DetailCheckRow(columnName = "Jenis Kelamin", value = "Laki-laki")
            DetailCheckRow(columnName = "Jenis Kelamin", value = "Laki-laki")
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}

@Composable
fun DetailCheckRow(
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
        DetailCheckScreen()
    }
}