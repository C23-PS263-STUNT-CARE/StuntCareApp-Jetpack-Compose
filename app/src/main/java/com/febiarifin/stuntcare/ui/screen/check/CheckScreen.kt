import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.model.dummyArticle
import com.febiarifin.stuntcare.model.dummyCheck
import com.febiarifin.stuntcare.ui.components.BottomSheetLayout
import com.febiarifin.stuntcare.ui.screen.check.CheckViewModel
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febiarifin.stuntcare.di.Injection
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.factory.CheckViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckViewModel = viewModel(
        factory = CheckViewModelFactory(Injection.provideCheckRepository())
    ),
    navigateToDetailCheck: (Long) -> Unit,
) {
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
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState){
                is UiState.Loading -> {
                    viewModel.getAllCheck()
                }
                is UiState.Success -> {
                    CheckList(
                        uiState.data,
                        navigateToDetailCheck = navigateToDetailCheck,
                    )
                }
                is UiState.Error -> {}
            }
        }

    }
}

@Composable
fun CheckList(
    listCheck: List<Check>,
    modifier: Modifier = Modifier,
    navigateToDetailCheck: (Long) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        if (listCheck.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
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
                            onClick = { /*TODO*/ },
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
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 80.dp)
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

@Preview
@Composable
fun CheckScreenPreview() {
    StuntCareTheme {
        CheckScreen(
            navigateToDetailCheck = {}
        )
    }
}