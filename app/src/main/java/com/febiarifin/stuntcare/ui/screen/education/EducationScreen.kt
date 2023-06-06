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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.ui.screen.education.EducationViewModel
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.di.Injection
import com.febiarifin.stuntcare.ui.common.UiState
import com.febiarifin.stuntcare.ui.factory.ViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationScreen(
    modifier: Modifier = Modifier,
    viewModel: EducationViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetailArticle: (Long) -> Unit
) {
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
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllArticle()
                }

                is UiState.Success -> {
                    EducationArticleList(
                        listArticle = uiState.data,
                        navigateToDetailArticle = navigateToDetailArticle
                    )
                }

                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun EducationArticleList(
    listArticle: List<Article>,
    modifier: Modifier = Modifier,
    navigateToDetailArticle: (Long) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        if (listArticle.isEmpty()) {
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
                Text(text = "Tidak Artikel", color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, 80.dp)
            ) {
                items(listArticle, key = { it.id }) { article ->
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