package upca.example.mynews

import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date

fun String.toServerDate() : Date?{
    //2026-10-13T14:15:23Z
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return format.parse(this)
}

fun String.encodeURL() : String {
    return URLEncoder.encode(this, "UTF-8")
}

fun Date.toShortTimeDate() : String {
    val format = SimpleDateFormat("yyyy/MM/dd HH:mm")
    return format.format(this)
}
