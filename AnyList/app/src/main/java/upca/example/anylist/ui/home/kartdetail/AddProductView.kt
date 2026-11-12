package upca.example.anylist.ui.home.kartdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.anylist.ui.theme.AnyListTheme
import upca.example.myshoppinglist.ui.home.kart.AddProductViewModel

@Composable
fun AddProductView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    kartId : String,
    productId : String? = null,
){

    val viewModel : AddProductViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val isEdit = productId != null

    LaunchedEffect(productId) {
        if (productId != null){
            viewModel.fetchProduct(kartId, productId)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.productName?:"",
            label = { Text("Name") },
            modifier = Modifier.padding(8.dp),
            onValueChange = {
                viewModel.editProductName(it)
            }
        )
        TextField(
            value = uiState.productQuantity.toString()?:"",
            label = { Text("Quantity") },
            modifier = Modifier.padding(8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = {
                viewModel.editProductQuantity(it.toDoubleOrNull()?:0.0)
            }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.addProduct(kartId,productId)
                navController.popBackStack()
            },
            enabled = !uiState.isLoading
        ) {
            Text(if(isEdit) "Edit Product" else "Add Product" )
            if (uiState.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp),
                    color = Color.White,
                )
            }
        }
        uiState.error?.let{
            Text(it)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun AddProductViewPreview() {
    AnyListTheme  {
        AddProductView(
            kartId = ""
        )
    }
}