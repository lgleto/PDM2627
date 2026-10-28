package upca.example.mynews.models

import org.json.JSONObject
import upca.example.mynews.toServerDate
import java.util.Date

class Article (
    var title : String? = null,
    var description : String? = null,
    var url : String? = null,
    var urlToImage : String? = null,
    var publishedAt : Date? = null,
){
    companion object {
        fun fromJson(articleJson: JSONObject) : Article{
            return Article(
                title = articleJson.getString("title"),
                description = articleJson.getString("description"),
                url = articleJson.getString("url"),
                urlToImage = articleJson.getString("urlToImage"),
                publishedAt = articleJson.getString("publishedAt").toServerDate()
            )
        }
    }
}

