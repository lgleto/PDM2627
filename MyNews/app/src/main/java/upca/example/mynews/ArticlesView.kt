package upca.example.mynews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date


@Composable
fun ArticlesView(
    modifier: Modifier = Modifier,
){
    val viewModel : ArticlesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    ArticlesViewContent(
        modifier = modifier,
        uiState = uiState
    )

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
}


@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    uiState : ArticlesViewState = ArticlesViewState()
){

    when {
        uiState.error != null ->{
            Box (
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    uiState.error
                )
            }
        }
        uiState.isLoading -> {
            Box (
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    items = uiState.articles
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
                        article.urlToImage?.let {
                            AsyncImage(
                                model = article.urlToImage,
                                contentDescription = null,
                            )
                        }?:run {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_foreground),
                                contentDescription = "news image"
                            )
                        }
                        Text(
                            text = article.publishedAt?.toString() ?: "",
                            fontSize = TextUnit(12f, TextUnitType.Sp)
                        )
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ArticlesViewPreview() {
    MyNewsTheme {
        ArticlesViewContent(
            uiState =
                ArticlesViewState(
                    articles = arrayListOf<Article>(
                        Article(
                            title = "As aulas começaram na UPCA",
                            description = "sdjwdkjw kjfhwse",
                            publishedAt = Date()
                        ),
                        Article(
                            title = "As aulas mobile são as melhores",
                            description = "sdjwdkjw kjfhwse",
                            urlToImage = "ddd",
                            publishedAt = Date()
                        ),
                    ),
                    error = null,
                    isLoading = false
                )
        )
    }
}