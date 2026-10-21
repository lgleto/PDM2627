package upca.example.mynews

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import upca.example.mynews.ui.theme.MyNewsTheme

@Composable
fun ArticleDetailView (
    modifier: Modifier = Modifier,
    url : String = ""
){
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.builtInZoomControls = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailViewPreview() {
    MyNewsTheme {
        ArticleDetailView()
    }
}