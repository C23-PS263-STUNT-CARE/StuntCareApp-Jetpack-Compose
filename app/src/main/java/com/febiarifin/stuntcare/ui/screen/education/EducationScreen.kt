import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.ui.screen.education.EducationViewModel
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.util.UserPreference

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationScreen(
    modifier: Modifier = Modifier,
    viewModel: EducationViewModel = hiltViewModel(),
    navigateToDetailArticle: (Long) -> Unit
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edukasi",
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
        viewModel.getAllArticle("Bearer " + userPreference.getUserToken().toString())
        EducationArticleList(
            listArticle = state.data,
            navigateToDetailArticle = navigateToDetailArticle
        )
        if (state.data == null) {
            ShowProgressBar(state = true, isFillMaxSize = true)
        }
    }
}

@Composable
fun EducationArticleList(
    listArticle: List<Article>? = null,
    modifier: Modifier = Modifier,
    navigateToDetailArticle: (Long) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        if (listArticle.isNullOrEmpty()) {
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
                Text(text = "Tidak Ada Artikel", color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 80.dp)
            ) {
                items(listArticle ?: emptyList(), key = { it.id }) { article ->
                    ArticleItem(
                        article,
                        500.dp,
                        200.dp,
                        modifier = Modifier.clickable {
                            navigateToDetailArticle(article.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun EducationScreenPreview() {
    StuntCareTheme {
        EducationScreen(
            navigateToDetailArticle = {}
        )
    }
}