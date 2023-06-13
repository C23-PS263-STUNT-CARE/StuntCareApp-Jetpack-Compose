package com.febiarifin.stuntcare.ui.screen.detail.article

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.ui.components.LoadGlideImage
import com.febiarifin.stuntcare.ui.components.ShowProgressBar
import com.febiarifin.stuntcare.util.UserPreference
import de.charlex.compose.HtmlText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailArticleScreen(
    articleId: Long,
    viewModel: DetailArticleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
) {
    val context = LocalContext.current
    val userPreference = UserPreference(context)
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Artikel",
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
        viewModel.getArticleById("Bearer "+ userPreference.getUserToken().toString(), articleId.toInt())
        if (state.data == null){
            ShowProgressBar(state = true, isFillMaxSize = true)
        }
        DetailArticleContent(article = state.data?.data)
    }
}

@Composable
fun DetailArticleContent(
    article: Article? = null,
) {
    if (article != null){
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(76.dp))

            LoadGlideImage(
                path = article.image_url,
                height = 210.dp,
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = buildAnnotatedString {
                        append("Diposting oleh ")
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append(article.author)
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                    ) {}
                }
                Text(
                    text = buildAnnotatedString {
                        append("Pada ")
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append(article.published_at)
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = "# " + article.label,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(20.dp))
            HtmlText(text = article.content, fontSize = 16.sp, textAlign = TextAlign.Justify)
        }
    }
}

@Preview
@Composable
fun DetailArticleScreenPreview() {
    StuntCareTheme {
        DetailArticleScreen(
            1,
            navigateToBack = {},
        )
    }
}