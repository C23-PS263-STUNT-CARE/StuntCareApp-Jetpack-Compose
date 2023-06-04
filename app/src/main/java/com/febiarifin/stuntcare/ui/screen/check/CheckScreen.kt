import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febiarifin.stuntcare.model.Article
import com.febiarifin.stuntcare.model.Check
import com.febiarifin.stuntcare.model.dummyArticle
import com.febiarifin.stuntcare.model.dummyCheck
import com.febiarifin.stuntcare.ui.theme.StuntCareTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Riwayat Cek Stunting",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    )
                },
                modifier = Modifier.border(
                    1.dp,
                    color = Color.Gray.copy(alpha = 0.3f)
                ),
//              navigationIcon = {
//                    IconButton(
//                        onClick = {}
//                    ) {
//                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                }
            )
        },
    ) {
        Column{
            Column {
                Spacer(modifier = Modifier.height(60.dp))
                CheckList(dummyCheck)
            }
        }
    }
}

@Composable
fun CheckList(
    listCheck: List<Check>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(0.dp,0.dp,0.dp,80.dp)
    ) {
        items(listCheck, key = { it.id }) { check ->
            CheckItem(
                check,
                modifier = Modifier.clickable{}
            )
        }
    }
}

@Preview
@Composable
fun CheckScreenPreview() {
    StuntCareTheme {
        CheckScreen()
    }
}