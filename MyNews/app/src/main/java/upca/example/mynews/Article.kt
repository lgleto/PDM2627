package upca.example.mynews

import org.json.JSONObject
import java.text.SimpleDateFormat
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

fun String.toServerDate() : Date?{
    //2026-10-13T14:15:23Z
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return format.parse(this)
}