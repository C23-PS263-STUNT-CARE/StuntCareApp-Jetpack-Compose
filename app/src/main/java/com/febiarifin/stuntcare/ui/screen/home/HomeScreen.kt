import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Info
import com.febiarifin.stuntcare.model.dummyArticle
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.ui.screen.home.HomeViewModel
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.util.UserPreference

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToFormCheck: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToInfo: () -> Unit,
    navigateToDetailArticle : (Long) -> Unit,
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val stateInfo by viewModel.stateInfo.collectAsStateWithLifecycle()
    val stateArticle by viewModel.stateArticle.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hai " + userPreference.getUserName(),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                    )
                },
                actions = {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(35.dp)
                                .clip(shape = CircleShape)
                                .background(Color.Gray.copy(alpha = 0.2f))
                                .clickable(onClick = {
                                    navigateToInfo()
                                }),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_notifications),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color.Red)
                        ) {}
                    }
                    Spacer(Modifier.width(10.dp))
                },
                modifier = Modifier.border(
                    1.dp,
                    color = Color.Gray.copy(alpha = 0.3f)
                ),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            BannerHeader(
                navigateToFormCheck = navigateToFormCheck
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeSection(
                title = stringResource(R.string.section_banner),
                content = {
                    viewModel.getAllInfo("Bearer " + userPreference.getUserToken().toString())
                    BannerRow(
                        listInfo = stateInfo.data,
                    )
                    if (stateInfo.data == null) {
                        ShowProgressBar(state = true, isFillMaxSize = true)
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeSection(
                title = stringResource(R.string.section_article),
                content = {
                    viewModel.getAllArticleLatest("Bearer " + userPreference.getUserToken().toString())
                    ArticleRow(
                        listArticle = stateArticle.data,
                        navigateToDetailArticle = navigateToDetailArticle,
                    )
                    if (stateArticle.data == null) {
                        ShowProgressBar(state = true, isFillMaxSize = true)
                    }
                }
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }

}

@Composable
private fun BannerHeader(
    modifier: Modifier = Modifier,
    navigateToFormCheck: () -> Unit,
) {
    Box(
        modifier = Modifier
            .height(290.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )

        Box(
            modifier = Modifier
                .height(210.dp)
                .width(300.dp)
                .align(Alignment.BottomCenter)
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
                    Text("Cek Status Stunting", color = Color.White)
                }
            }
        }
    }

}

@Composable
private fun BannerRow(
    modifier: Modifier = Modifier,
    listInfo: List<Info>? = null,
) {
    val context = LocalContext.current
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(listInfo ?: emptyList(), key = { it.id }) { info ->
            BannerItem(
                info,
                modifier = modifier
                    .width(300.dp)
                    .height(120.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(info.url))
                        context.startActivity(intent)
                    }
            )
        }
    }
}

@Composable
fun ArticleRow(
    listArticle: List<Article>? = null,
    modifier: Modifier = Modifier,
    navigateToDetailArticle : (Long) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listArticle ?: emptyList(), key = { it.id }) { article ->
            ArticleItem(
                article,
                200.dp,
                120.dp,
                modifier = Modifier.clickable {
                    navigateToDetailArticle(article.id)
                }
            )
        }
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StuntCareTheme {
        HomeScreen(navigateToFormCheck = {}, navigateToInfo = {}, navigateToDetailArticle = {})
    }
}