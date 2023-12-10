package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialSetUpScreen(addScreen: () -> Unit) {
    Column {
        CenterAlignedTopAppBar(title = { Text(text = "Welcome GM!")}, modifier = Modifier.shadow(4.dp))
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(onClick = { addScreen() }, modifier = Modifier.background(Color.LightGray)) {
                Text(text = "New Campaign")
            }
            TextButton(onClick = { addScreen() }, modifier = Modifier.background(Color.LightGray)) {
                Text(text = "New One-Shot")
            }
        }
        


    }
}

@Preview (showSystemUi = true)
@Composable
fun InitialPreview() {
    InitialSetUpScreen{}
}