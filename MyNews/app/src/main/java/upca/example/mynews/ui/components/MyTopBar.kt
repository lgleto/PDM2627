package upca.example.mynews.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.R
import upca.example.mynews.ui.theme.MyNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar (
    navController: NavController = rememberNavController(),
    title : String = "My News",
    isBaseScreen : Boolean = true,
    actions : @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        },
        navigationIcon = {
            if (!isBaseScreen) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_arrow_back_ios_24),
                        contentDescription = "button back"
                    )
                }
            }
        },
        actions = {
            actions()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopBarPreview() {
    MyNewsTheme() {
        MyTopBar()
    }
}