package upca.example.anylist.ui.components

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
import upca.example.anylist.R
import upca.example.anylist.ui.theme.AnyListTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title : String = "Top News",
    navController: NavController = rememberNavController(),
    isBaseScreen : Boolean = true,
    actions : @Composable () -> Unit = {}
){
    TopAppBar(
        title = {
            Text(title,
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
                        painter = painterResource(id = R.drawable.outline_arrow_back_24),
                        contentDescription = "back"
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
fun MyTopBarPreview(){
    AnyListTheme  {
        MyTopBar()
    }
}