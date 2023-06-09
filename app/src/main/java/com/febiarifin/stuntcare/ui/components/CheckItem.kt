import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.febiarifin.stuntcare.R
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun CheckItem(
    check: Check,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.dp,
                shape = RoundedCornerShape(4.dp),
                color = if (check.status_stunting >= 1) Color.Yellow.copy(alpha = 0.7f) else Color.Green.copy(alpha = 0.7f)
            ),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (check.status_stunting >= 1) Color.Yellow.copy(alpha = 0.2f) else Color.Green.copy(alpha = 0.2f),
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(8.dp),
        ) {
            Box(
                modifier = modifier
                    .size(45.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.baby_boy),
                    contentDescription = null,
                    modifier = modifier.size(30.dp)
                )
            }
            Spacer(modifier = modifier.width(20.dp))
            Text(
                text = check.name,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckItemPreview() {
    StuntCareTheme {
        CheckItem(
            Check(2, "Anak 2", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.6),
        )
    }
}