package upca.example.mynews;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.Date

class ArticlesViewModel : ViewModel() {
    private val _articles = MutableStateFlow(arrayListOf<Article>())
    val articles : StateFlow<List<Article>> = _articles.asStateFlow()

    fun fetchArticles(){
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://anewsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
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
                    viewModelScope.launch(Dispatchers.Main) {
                        _articles.value = articlesResult
                    }

                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }
    }

}
