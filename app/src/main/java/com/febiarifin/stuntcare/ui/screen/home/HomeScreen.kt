import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.dummyArticle
import com.febiarifin.stuntcare.model.dummyBanner
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hai Febi Arifin",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                    )
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(shape = CircleShape)
                            .background(Color.Gray.copy(alpha = 0.2f))
                            .clickable(onClick = {}),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_notifications),
                            contentDescription = null,
                            tint = Color.Black
                        )
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
            BannerHeader()
            Spacer(modifier = Modifier.height(24.dp))
            HomeSection(
                title = stringResource(R.string.section_banner),
                content = {BannerRow()}
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeSection(
                title = stringResource(R.string.section_article),
                content = {   ArticleRow(dummyArticle)}
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }

}

@Composable
private fun BannerHeader(
    modifier: Modifier = Modifier,
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
                    onClick = { /*TODO*/ },
                    colors =  ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
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

}

@Composable
private fun BannerRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ){
        items(dummyBanner, key = {it.text}){banner->
            BannerItem(
                banner,
                modifier = Modifier.clickable{}
            )
        }
    }
}

@Composable
fun ArticleRow(
    listArticle: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(listArticle, key = {it.id}){ article ->
            ArticleItem(
                article,
                200.dp,
                120.dp,
                modifier = Modifier.clickable{}
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
        HomeScreen()
    }
}