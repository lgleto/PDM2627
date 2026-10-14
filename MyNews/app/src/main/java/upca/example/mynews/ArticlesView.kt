package upca.example.mynews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier
){
    var articles = arrayListOf<Article>(
        Article(
            title = "As aulas começaram na UPCA",
            description = "sdjwdkjw kjfhwse",
            publishedAt = Date()
        ),
        Article(
            title = "As aulas mobile são as melhores",
            description = "sdjwdkjw kjfhwse",
            publishedAt = Date()
        ),
    )

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = articles
        ){ index, article ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .padding(8.dp)
            ) {
                Text(
                    text = article.title ?: "",
                    fontSize = TextUnit(26f, TextUnitType.Sp)
                )
                Text(
                    text = article.description ?: "",
                    fontSize = TextUnit(16f, TextUnitType.Sp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "news image"
                )
                Text(
                    text = article.publishedAt?.toString() ?: "",
                    fontSize = TextUnit(12f, TextUnitType.Sp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesViewPreview(){
    MyNewsTheme {
        ArticlesView()
    }
}