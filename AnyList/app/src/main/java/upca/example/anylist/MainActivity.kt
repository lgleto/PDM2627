package upca.example.anylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import upca.example.anylist.ui.home.KartView
import upca.example.anylist.ui.login.LoginView
import upca.example.anylist.ui.theme.AnyListTheme
import upca.example.myshoppinglist.ui.home.kart.AddKartView

const val TAG = "AnyList"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnyListTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navController.navigate(Screen.Home.route)
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable( route = Screen.Home.route ){
                            KartView(
                                navController = navController
                            )
                        }
                        composable( route = Screen.AddKart.route ){
                            AddKartView(
                                navController = navController
                            )
                        }
                        composable (route = Screen.Login.route) {
                            LoginView(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String){
    object Home : Screen ("home")
    object Login : Screen ("login")

    object AddKart : Screen("add_kart")

}