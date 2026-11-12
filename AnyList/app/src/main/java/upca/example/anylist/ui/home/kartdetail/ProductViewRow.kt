package upca.example.anylist.ui.home.kartdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import upca.example.anylist.models.Product
import upca.example.anylist.ui.theme.AnyListTheme


@Composable
fun ProductViewRow(
    modifier: Modifier = Modifier,
    product: Product,
    onChecked : (Boolean)->Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            product.name ?: "",
            modifier = Modifier
                .weight(1f)
        )
        Text(
            product.quantity.toString() ?: "",
            maxLines = 1
        )
        Checkbox(
            checked = product.checked?:false,
            onCheckedChange = onChecked
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProductViewPreview() {
    AnyListTheme {
        ProductViewRow(
            product = Product(
                name = "Canetas",
                quantity = 10.0,
                checked = false
            )
        )
    }
}