import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Info
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BannerItem(
    info: Info,
    modifier: Modifier = Modifier,
) {
    GlideImage(
        model = info.image_url,
        contentDescription = "Image",
        modifier = modifier
            .height(120.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                1.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color.Gray.copy(alpha = 0.2f)
            ),
        contentScale = ContentScale.Crop
    ) {
        it
            .error(R.drawable.lazy_load)
            .placeholder(R.drawable.lazy_load)
            .load(info.image_url)
    }

}

@Preview(showBackground = true)
@Composable
fun BannerItemPreview() {
    StuntCareTheme {
        BannerItem(
            info = Info(
                1,
                "https://storage.googleapis.com/banner-stuntcare/info/3f3d276953a9d7833f3d276953a9d783a39dc30cbebas_stunting.jpg",
                "https://github.com"
            )
        )
    }
}