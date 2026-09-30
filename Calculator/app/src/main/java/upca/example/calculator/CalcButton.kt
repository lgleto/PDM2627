package upca.example.calculator

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import upca.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    label : String = "0",
    isOperation:  Boolean = false,
    onClick : (String)->Unit
){
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOperation)
                MaterialTheme.colorScheme.secondary
            else
                MaterialTheme.colorScheme.tertiary
        ),
        onClick = {
            onClick(label)
        }
    ) {
        Text(
            text = label,
            fontSize = TextUnit(50.0f, TextUnitType.Sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview(){
    CalculatorTheme() {
        CalcButton(){}
    }
}