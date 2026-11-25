package upca.example.spacefighter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.spacefighter.ui.theme.SpaceFighterTheme

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController : NavController = rememberNavController()
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            painter = painterResource(R.drawable.splash),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(40.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.playnow),
                contentDescription = "play now",
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .clickable{
                        navController.navigate("game")
                    },
            )
            Image(
                painter = painterResource(R.drawable.highscore),
                contentDescription = "highscore",
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .clickable{

                    },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    SpaceFighterTheme() {
        HomeView()
    }
}