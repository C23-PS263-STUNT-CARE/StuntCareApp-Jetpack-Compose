import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun EducationScreen() {
    Text("Education Screen")
}

@Preview
@Composable
fun EducationScreenPreview() {
    StuntCareTheme {
        EducationScreen()
    }
}