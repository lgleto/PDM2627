package upca.example.anylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import upca.example.anylist.ui.home.KartView
import upca.example.anylist.ui.login.LoginView
import upca.example.anylist.ui.theme.AnyListTheme
import upca.example.anylist.ui.components.MyTopBar
import upca.example.anylist.ui.home.kartdetail.KartDetailView
import upca.example.myshoppinglist.ui.home.kart.AddKartView
import upca.example.anylist.ui.home.kartdetail.AddProductView

const val TAG = "AnyList"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var title by remember { mutableStateOf("My Shopping List") }
            var isBaseScreen by remember { mutableStateOf(true) }
            var topBarActions by remember {mutableStateOf<@Composable () -> Unit>({})}

            LaunchedEffect(Unit) {
                val currentUser = Firebase.auth.currentUser
                if (currentUser != null) {
                    navController.navigate(Screen.Home.route)
                } else {
                    navController.navigate(Screen.Login.route)
                }
            }

            AnyListTheme {
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
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable(route = Screen.Home.route) {
                            isBaseScreen = true
                            title = "Home"

                            topBarActions = {
                                Text("Logout",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable
                                        {
                                            Firebase.auth.signOut()
                                            navController.navigate(Screen.Login.route)
                                        }
                                )
                            }
                            KartView(
                                navController = navController
                            )
                        }
                        composable(route = Screen.Login.route) {
                            isBaseScreen = true
                            title = "Login"
                            topBarActions = {}
                            LoginView(
                                navController = navController
                            )
                        }
                        composable(route = Screen.AddKart.route) {
                            isBaseScreen = false
                            title = "Add Kart"
                            topBarActions = {}
                            AddKartView(
                                navController = navController
                            )
                        }
                        composable(route = Screen.KartDetail.route) { backStateEntry ->
                            val kartId = backStateEntry.arguments?.getString("kartId")!!
                            isBaseScreen = false
                            title = "Kart"
                            topBarActions = {}
                            KartDetailView(
                                navController = navController,
                                kartId = kartId
                            )
                        }
                        composable(route = Screen.AddProduct.route) { backStateEntry ->
                            val kartId = backStateEntry.arguments?.getString("kartId")!!
                            var productId = backStateEntry.arguments?.getString("productId")
                            productId = if (productId.isNullOrEmpty()) null else productId
                            isBaseScreen = false
                            title = "Product"
                            topBarActions = {}
                            AddProductView(
                                navController = navController,
                                kartId = kartId,
                                productId = productId
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
    object Login : Screen("login")
    object AddKart : Screen ("add_kart")
    object KartDetail : Screen ("kart_detail/{kartId}")
    object AddProduct : Screen ("add_product/{kartId}/{productId}")
}