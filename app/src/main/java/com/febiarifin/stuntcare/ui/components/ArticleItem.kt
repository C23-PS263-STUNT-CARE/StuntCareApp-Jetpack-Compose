import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun ArticleItem(
    article: Article,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(width)
            .border(
                1.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color.Gray.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column {
            Image(
                painter = painterResource(article.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            )
        }
        Column(
            modifier = modifier
                .padding(8.dp)
        ){
            Box(
                modifier = modifier
                    .width(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                    .padding(3.dp, 1.dp, 3.dp, 1.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = article.label,
                    fontSize = 10.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = article.date,
                fontSize = 12.sp,
                color = Color.Gray.copy(alpha = 0.8f),
            )
        }
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    StuntCareTheme {
        ArticleItem(
            article = Article(
                1,
                R.drawable.article_image_1,
                "Lorem ipsum dolor sit amet conse ctetur adipiscing elit",
                "Lorem",
                "29 Mei 2023",
                "Lorem ipsum dolor sit amet conse ctetur adipiscing elit",
                "Admin"
            ),
            200.dp,
            120.dp,
        )
    }
}