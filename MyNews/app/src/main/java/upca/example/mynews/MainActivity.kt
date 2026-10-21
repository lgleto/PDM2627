package upca.example.mynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.ui.theme.MyNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Articles.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(Screen.Articles.route){
                            ArticlesView(
                                navController = navController
                            )
                        }
                        composable(Screen.ArticleDetail.route){ backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title")
                            val url = backStackEntry.arguments?.getString("url")
                            ArticleDetailView(
                                url = url ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}


sealed class Screen(val route: String) {
    object Articles : Screen("articles")
    object ArticleDetail : Screen("article_detail/{title}/{url}")
}

