import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Banner
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun BannerItem(
    banner: Banner,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(banner.image),
        contentDescription = null,
        modifier = modifier
            .width(300.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun BannerItemPreview() {
    StuntCareTheme {
        BannerItem(banner = Banner(R.drawable.banner_image_1, R.string.banner_1), modifier = Modifier.padding(horizontal = 8.dp))
    }
}