import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@Composable
fun ProfileScreen() {
    Text("Profile Screen")
}

@Preview
@Composable
fun ProfileScreenPreview() {
    StuntCareTheme {
        ProfileScreen()
    }
}