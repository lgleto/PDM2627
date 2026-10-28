package upca.example.mynews

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.ui.components.MyBottomBar
import upca.example.mynews.ui.components.MyTopBar
import upca.example.mynews.ui.home.ArticleDetailView
import upca.example.mynews.ui.home.ArticlesView
import upca.example.mynews.ui.theme.MyNewsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var title by remember { mutableStateOf("My News") }
            var isBaseScreen by remember { mutableStateOf(true) }
            var topBarActions by remember { mutableStateOf<@Composable () -> Unit>({}) }
            MyNewsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MyTopBar(
                            navController = navController,
                            title = title,
                            isBaseScreen = isBaseScreen,
                            actions = topBarActions
                        )
                    },
                    bottomBar = {
                        MyBottomBar(
                            navController = navController
                        )
                    }
                    ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(Screen.Home.route){
                            title = "My News"
                            isBaseScreen = true
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = ""
                            )
                        }
                        composable(Screen.Health.route){
                            title = "Health"
                            isBaseScreen = true
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "health"
                            )
                        }
                        composable(Screen.Sports.route){
                            title = "Sports"
                            isBaseScreen = true
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "sports"
                            )
                        }
                        composable(Screen.Technology.route){
                            title = "Technology"
                            isBaseScreen = true
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "technology"
                            )
                        }
                        composable(Screen.ArticleDetail.route){ backStackEntry ->
                            title = backStackEntry.arguments?.getString("title")?:""
                            val url = backStackEntry.arguments?.getString("url")
                            topBarActions = {
                                IconButton(
                                    onClick = {
                                        val intentShare = Intent(Intent.ACTION_SEND)
                                        intentShare.setType("text/plain")
                                        intentShare.putExtra(Intent.EXTRA_SUBJECT,title)
                                        intentShare.putExtra(Intent.EXTRA_TEXT,url)
                                        startActivity(Intent.createChooser(intentShare,"Partilhar"))
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.outline_share_24),
                                        contentDescription = "button back"
                                    )
                                }
                            }
                            isBaseScreen = false
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
    object Home : Screen("home")
    object Health : Screen("health")
    object Sports : Screen("sports")
    object Technology : Screen("technology")
    object ArticleDetail : Screen("article_detail/{title}/{url}")
}

