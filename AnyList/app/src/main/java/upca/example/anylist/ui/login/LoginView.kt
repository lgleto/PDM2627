package upca.example.anylist.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.anylist.Screen
import upca.example.anylist.ui.theme.AnyListTheme

@Composable
fun LoginView (
    modifier: Modifier = Modifier,
    navController : NavController = rememberNavController()
){

    val viewModel : LoginViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = uiState.email,
            label = {
                Text("Email")
            },
            onValueChange = {
                viewModel.updateEmail(it)
            },
            modifier = Modifier.padding(8.dp)
        )
        TextField(
            value = uiState.password,
            label = {
                Text("Password")
            },
            onValueChange = {
                viewModel.updatePassword(it)
            },
            modifier = Modifier.padding(8.dp)
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.login {
                    navController.navigate(Screen.Home.route)
                }
            },
            enabled = !uiState.isLoading
        ) {
            Text("Login")
            if(uiState.isLoading){
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        uiState.error?.let {
            Text(
                uiState.error?:"",
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview(){
    AnyListTheme {
        LoginView()
    }
}