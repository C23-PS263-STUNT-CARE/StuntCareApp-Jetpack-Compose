import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun StuntCareApp(
    modifier: Modifier = Modifier
) {
    
}

@Preview(showBackground = true)
@Composable
fun StuntCareAppPreview() {
    StuntCareTheme {
        StuntCareApp()
    }
}