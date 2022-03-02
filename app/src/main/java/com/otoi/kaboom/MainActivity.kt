package com.otoi.kaboom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otoi.kaboom.ui.theme.KaboomTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaboomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   BombControls()
                }
            }
        }
    }
}

@Composable
fun BombControls() {

    var btn1Presses by rememberSaveable { mutableStateOf((0..5).random()) }
    var btn1Pressed by rememberSaveable { mutableStateOf(0) }
    var btn2Presses by rememberSaveable { mutableStateOf((0..5).random()) }
    var btn2Pressed by rememberSaveable { mutableStateOf(0) }
    var btn3Presses by rememberSaveable { mutableStateOf((0..5).random()) }
    var btn3Pressed by rememberSaveable { mutableStateOf(0) }
    var codeWords   by rememberSaveable { mutableStateOf("") }
    var password    by rememberSaveable { mutableStateOf("") }
    var redWireConnected = rememberSaveable {mutableStateOf(true) }
    var greenWireConnected = rememberSaveable {mutableStateOf(true) }
    var blueWireConnected = rememberSaveable {mutableStateOf(true) }
    val padding = 16.dp


    fun generateInstructions(): String {
        var instructions = mutableListOf<String>()

        if(btn1Presses > 0){
            instructions.add("Press 'Button 1 ' $btn1Presses ${if (btn1Presses == 1) "time" else "times"}")
        }
        if(btn2Presses > 0){
            instructions.add("Press 'Button 2 ' $btn2Presses ${if (btn2Presses == 1) "time" else "times"}")
        }
        if(btn3Presses > 0){
            instructions.add("Press 'Button 3 ' $btn3Presses ${if (btn3Presses == 1) "time" else "times"}")
        }

        //.........

    return instructions.joinToString(separator = ". ", postfix = ".")
    }

    Column( modifier = Modifier.padding(padding)){
    Text(text = "KAB00M", modifier = Modifier.align(Alignment.CenterHorizontally))
    Text(text = "Disarm the Bomb by following the instructions.", modifier = Modifier.padding(padding))
    Text(text = generateInstructions())
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(padding / 2), horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick =  { btn1Pressed += 1 }){
            Text("1")
            }
        Button(onClick =  { btn2Pressed += 1 }){
            Text("2")
        }
        Button(onClick =  { btn3Pressed += 1 }) {
            Text("3")
        }
        }
    TextField(
            modifier = Modifier.fillMaxWidth().padding(padding),
            value = codeWords,
            onValueChange = { codeWords = it },
            label = { Text("Code Words") },
            singleLine = true
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Red wire is connected", color = Color.Red)
            Switch(
                checked = redWireConnected.value,
                onCheckedChange = {redWireConnected.value = it}
            )

        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Green wire is connected", color = Color.Green)
            Switch(
                checked = greenWireConnected.value,
                onCheckedChange = {redWireConnected.value = it}
            )

        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text("Blue wire is connected", color = Color.Blue)
            Switch(
                checked = blueWireConnected.value,
                onCheckedChange = {blueWireConnected.value = it}
            )

        }
    TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(padding).align(Alignment.CenterHorizontally)
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(padding / 2), horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = { /*TODO*/ },colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)) {
                Text("Defuse")
            }
            Button( onClick = { /*TODO*/ }) {
                Text("Try Again")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KaboomTheme {
        BombControls();
    }
}