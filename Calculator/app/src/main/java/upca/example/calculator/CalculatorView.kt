package upca.example.calculator


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

import upca.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorView(
       modifier: Modifier = Modifier
) {

    var displayText by remember { mutableStateOf("0") }
    var calculatorBrain by remember { mutableStateOf(CalculatorBrain()) }
    var userIsInTheMiddleOfIntroduction by remember { mutableStateOf(false ) }

    val onNumberPressed : (String) -> Unit = { num ->
        if (userIsInTheMiddleOfIntroduction) {
            if (!(displayText.contains(".") && num == ".")) {
                if (displayText == "0") {
                    if (num == ".") {
                        displayText += num
                    } else {
                        displayText = num
                    }
                } else {
                    displayText += num
                }
            }
        }else{
            displayText = num
        }

        userIsInTheMiddleOfIntroduction = true
    }

    val onOperationPressed : (String) -> Unit = { op ->

        calculatorBrain.doOperation(displayText.toDouble())
        var result = calculatorBrain.accumulator

        if (result % 1.0 == 0.0){
            displayText = "${result.toInt()}"
        }else {
            displayText = "${result}"
        }
        calculatorBrain.operation = Operation.getOperation(op)

        userIsInTheMiddleOfIntroduction = false

    }


    val onUnaryOperationPressed : (String) -> Unit = { op ->
        calculatorBrain.operation = Operation.getOperation(op)
        calculatorBrain.doOperation(displayText.toDouble())
        var result = calculatorBrain.accumulator

        if (result % 1.0 == 0.0){
            displayText = "${result.toInt()}"
        }else {
            displayText = "${result}"
        }

        userIsInTheMiddleOfIntroduction = false

    }

    Column( modifier = modifier.fillMaxSize() ) {
        Text(
            text = displayText,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Right,
            fontSize = TextUnit(70.0f, TextUnitType.Sp)
        )
        Row (modifier = Modifier.weight(1f)) {
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "C" , isOperation = true,
                onClick = {
                    calculatorBrain.operation = null
                    calculatorBrain.accumulator = 0.0
                    displayText = "0"
                } )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "%" , isOperation = true, onClick = onUnaryOperationPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "√" , isOperation = true, onClick = onUnaryOperationPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "±" , isOperation = true, onClick = onUnaryOperationPressed )
        }
        Row (modifier = Modifier.weight(1f)) {
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "7" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "8" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "9" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "+" , isOperation = true, onClick = onOperationPressed )
        }
        Row (modifier = Modifier.weight(1f)) {
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "4" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "5" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "6" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "-" , isOperation = true, onClick = onOperationPressed )
        }
        Row (modifier = Modifier.weight(1f)) {
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "1" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "2" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "3" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "×" , isOperation = true, onClick = onOperationPressed )
        }
        Row {
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "0" , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "." , onClick = onNumberPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "=" , isOperation = true, onClick = onOperationPressed )
            CalcButton( modifier = Modifier
                .weight(1f)
                .padding(4.dp), label = "÷" , isOperation = true, onClick = onOperationPressed )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CalculatorViewPreview(){
    CalculatorTheme {
        CalculatorView()
    }
}