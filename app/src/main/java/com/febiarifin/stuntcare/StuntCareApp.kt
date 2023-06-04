import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun StuntCareApp(
    modifier: Modifier = Modifier
) {
    HomeScreen()
}

@Composable
fun StuntCareLightTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF459FEB),
            onPrimary = Color.White,
            secondary = Color(0xFF3984E9),
            onSecondary = Color.White,
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun StuntCareAppPreview() {
    StuntCareLightTheme {
        StuntCareApp()
    }
}