package upca.example.mynews.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import upca.example.mynews.R
import upca.example.mynews.models.Article
import upca.example.mynews.toShortTimeDate
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date

@Composable
fun ArticleViewRow (
    modifier: Modifier = Modifier,
    article: Article
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        Text(
            text = article.title ?: "",
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            article.description ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onTertiary)
                    .height(200.dp),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                model = article.urlToImage ?: "no image",
                contentDescription = "image article",
                contentScale = ContentScale.Crop
            )
            Text((article.publishedAt ?: Date()).toShortTimeDate(),
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(2.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleViewRowPreview() {
    MyNewsTheme() {
        ArticleViewRow(
            article = Article(
                title = "As aulas mobile são as melhores jhsdgfuhjwegfjsegdfjsdgfjsdg jsg fu yaegfsiuqasf auwfg iquawfg qiuwfgqwuifg quiwfyg ",
                description = "sdjwdkjw kjfhwse",
                urlToImage = "ddd"
            )
        )
    }
}

