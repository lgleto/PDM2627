package upca.example.mynews.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.R
import upca.example.mynews.Screen
import upca.example.mynews.ui.theme.MyNewsTheme

data class BottomBarItem(
    val icon : Int,
    val label : String,
    val route : String
)
@Composable
fun MyBottomBar (
    navController: NavController = rememberNavController(),
) {

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        BottomBarItem(
            icon = R.drawable.house_solid_full,
            label = "Home",
            route = Screen.Home.route
        ),
        BottomBarItem(
            icon = R.drawable.heart_solid_full,
            label = "Health",
            route = Screen.Health.route
        ),
        BottomBarItem(
            icon = R.drawable.person_running_solid_full,
            label = "Sports",
            route = Screen.Sports.route
        ),
        BottomBarItem(
            icon = R.drawable.robot_solid_full,
            label = "Technology",
            route = Screen.Technology.route
        )
    )


    BottomAppBar() {
        NavigationBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = {
                        navController.navigate(item.route)
                        selectedIndex = index
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = "home",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(item.label) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyBottomBarPreview() {
    MyNewsTheme() {
        MyBottomBar()
    }
}