import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.screen.check.CheckViewModel
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.util.UserPreference

private val colorPrimary: Color = Color(0xFF3984E9)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScreen(
    modifier: Modifier = Modifier,
    navigateToDetailCheck: (Long) -> Unit,
    navigateToFormCheck: () -> Unit,
    viewModel: CheckViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Riwayat Cek Stunting",
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
            )
        },
        floatingActionButton = {
            if (!state.data.isNullOrEmpty()) {
                FloatingButton {
                    navigateToFormCheck()
                }
            }
        }
    ) {
        viewModel.getAllCheckHistory(
            "Bearer " + userPreference.getUserToken().toString(),
            userPreference.getUserId().toString()
        )
        CheckList(
            state.data,
            navigateToDetailCheck = navigateToDetailCheck,
            navigateToFormCheck = navigateToFormCheck,
        )
        if (state.data == null) {
            ShowProgressBar(state = true, isFillMaxSize = true)
        }
    }
}

@Composable
fun CheckList(
    listCheck: List<Check>? = null,
    modifier: Modifier = Modifier,
    navigateToDetailCheck: (Long) -> Unit,
    navigateToFormCheck: () -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        if (listCheck.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(210.dp)
                        .width(300.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(
                            1.dp,
                            shape = RoundedCornerShape(16.dp),
                            color = Color.Gray.copy(alpha = 0.3f)
                        ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(90.dp),
                            painter = painterResource(R.drawable.banner_header),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.height(8.dp))
                        Text(
                            fontSize = 14.sp,
                            text = stringResource(R.string.jargon_1),
                            color = Color.Gray
                        )
                        Spacer(modifier = modifier.height(8.dp))
                        Button(
                            onClick = { navigateToFormCheck() },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_add),
                                contentDescription = null
                            )
                            Spacer(modifier = modifier.width(6.dp))
                            Text("Cek Status Stunting")
                        }
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.padding(bottom = 80.dp)
            ) {
                items(listCheck, key = { it.id }) { check ->
                    CheckItem(
                        check,
                        modifier = Modifier.clickable {
                            navigateToDetailCheck(check.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(bottom = 80.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = colorPrimary,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun CheckScreenPreview() {
    StuntCareTheme {
        CheckScreen(
            navigateToDetailCheck = {},
            navigateToFormCheck = {},
        )
    }
}