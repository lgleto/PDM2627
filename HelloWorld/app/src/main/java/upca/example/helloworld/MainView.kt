package upca.example.helloworld

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainView(
    modifier : Modifier = Modifier
){

    var text by remember { mutableStateOf("Hello World!")}

    Column(
        modifier = modifier
    )  {
        Text(text)
        Button(
            onClick = {
                text = "Olá mundo!"
            }
        ){
            Text("Traduzir")
        }
    }

}