import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun HomeScreen() {

}

@Composable
fun BannerHeader(
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
                .background(Color(0xFF459FEB))
        ){
            Row(
                modifier = Modifier
                    .width(290.dp)
                    .align(Alignment.TopCenter)
                    .padding(0.dp, 40.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hai Febi Arifin",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(shape = CircleShape)
                        .background(Color.White)
                        .clickable(onClick = {}),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_notifications),
                        contentDescription = null,
                        tint = Color.Blue
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .align(Alignment.BottomCenter)
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .shadow(
                    elevation = 2.dp,
                    shape = MaterialTheme.shapes.large,
                ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier
                        .size(90.dp),
                    painter = painterResource(R.drawable.banner_header),
                    contentDescription = null
                )
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    fontSize = 14.sp,
                    text = stringResource(R.string.jargon_1),
                    color = Color.Gray
                )
                Spacer(modifier = modifier.height(6.dp))
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.width(6.dp))
                    Text("Buat Prediksi")
                }
            }
        }
    }

}

@Composable
fun BannerRow() {
    
}


@Preview(showBackground = true)
@Composable
fun BannerHeaderPreview() {
    StuntCareTheme {
        BannerHeader()
    }
}