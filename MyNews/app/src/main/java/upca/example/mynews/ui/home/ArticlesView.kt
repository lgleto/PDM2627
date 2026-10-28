package upca.example.mynews.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.Screen
import upca.example.mynews.encodeURL
import upca.example.mynews.models.Article
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date


@Composable
fun ArticlesView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    category : String = ""
){
    val viewModel : ArticlesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    ArticlesViewContent(
        modifier = modifier,
        uiState = uiState,
        navController = navController
    )

    LaunchedEffect(Unit) {
        viewModel.fetchArticles(category = category)
    }
}


@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    uiState : ArticlesViewState = ArticlesViewState(),
    navController: NavController = rememberNavController()
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
                    ArticleViewRow(
                        modifier = Modifier
                            .clickable{
                                navController.navigate(
                                    Screen.ArticleDetail.route
                                        .replace("{title}", article.title?:"")
                                        .replace("{url}", article.url?.encodeURL()?:"")
                                )
                            },
                        article = article
                    )
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