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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import upca.example.mynews.Article
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date
import kotlin.collections.arrayListOf

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier,
){

    var articles by remember { mutableStateOf(arrayListOf<Article>()) }

    ArticlesViewContent(
        modifier = modifier,
        articles = articles
    )

    LaunchedEffect(articles) {
        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
                .build()
            try {
                val response = client.newCall(request).execute()
                val result = response.body!!.string()
                val resultJson = JSONObject(result)

                val status = resultJson.getString("status")
                if(status == "ok"){
                    val articlesJson = resultJson.getJSONArray("articles")
                    val articlesResult = arrayListOf<Article>()
                    for ( index in 0..<articlesJson.length()){
                        val articleJson = articlesJson.getJSONObject(index)
                        val article = Article(
                            title = articleJson.getString("title"),
                            description = articleJson.getString("description"),
                            url = articleJson.getString("url"),
                            urlToImage = articleJson.getString("urlToImage"),
                            publishedAt = Date()//articleJson.getString("publishedAt")
                        )
                        articlesResult.add(article)
                    }
                    GlobalScope.launch(Dispatchers.Main) {
                        articles = articlesResult
                    }

                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }
    }
}


@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    articles : List<Article> = arrayListOf()
){

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
        ArticlesViewContent(
            articles = arrayListOf<Article>(
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
        )
    }
}