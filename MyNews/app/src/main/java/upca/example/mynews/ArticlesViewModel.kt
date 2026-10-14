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

data class ArticlesViewState (
    val articles : List<Article> = arrayListOf(),
    val error : String? = null,
    val isLoading : Boolean = false
)

class ArticlesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesViewState())
    val uiState : StateFlow<ArticlesViewState> = _uiState.asStateFlow()

    fun fetchArticles(){
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )
        viewModelScope.launch(Dispatchers.IO) {
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
                        articlesResult.add(Article.fromJson(articleJson))
                    }
                    viewModelScope.launch(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            articles = articlesResult,
                            isLoading = false
                        )
                    }

                }
            } catch (e: Exception) {
                println(e.localizedMessage)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.localizedMessage
                )
            }
        }
    }

}
