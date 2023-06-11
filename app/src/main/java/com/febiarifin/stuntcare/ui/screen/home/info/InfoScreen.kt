import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.model.Info
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.screen.home.HomeViewModel
import com.febiarifin.stuntcare.util.UserPreference

private val colorPrimary: Color = Color(0xFF3984E9)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToBack : () -> Unit,
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.stateInfo.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Info Kesehatan",
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
        viewModel.getAllInfo(
            "Bearer " + userPreference.getUserToken().toString(),
        )
        InfoList(
            null,
        )
        if (state.data == null){
            ShowProgressBar(state = true, isFillMaxSize = true)
        }
    }
}

@Composable
fun InfoList(
    listInfo: List<Info>? = null,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        if (listInfo.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(bottom = 120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_data),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Text(text = "Tidak Ada Info Penting", color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(listInfo, key = { it.id }) { info ->
                    BannerItem(
                        info = info,
                        modifier = modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(info.url))
                            context.startActivity(intent)
                        }
                    )
                }
            }
            Spacer(modifier = modifier.height(30.dp))
        }
    }
}


@Preview
@Composable
fun InfoScreenPreview() {
    StuntCareTheme {
        InfoScreen(
            navigateToBack = {}
        )
    }
}