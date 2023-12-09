package com.example.dndtools.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialSetUpScreen() {
    Column {
        CenterAlignedTopAppBar(title = { Text(text = "Welcome GM!")}, modifier = Modifier.shadow(4.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f).align(Alignment.CenterHorizontally)) {
                Text(text = "New Campaign")
            }

        }
    }
}

@Preview (showSystemUi = true)
@Composable
fun InitialPreview() {
    InitialSetUpScreen()
}