package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.viewmodels.IntroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(introViewModel: IntroViewModel = viewModel(), addScreen: (Any?) -> Unit) {
    var expanded by remember {mutableStateOf(false)}

    Column {
        CenterAlignedTopAppBar(title = { Text(text = "Welcome GM!")}, modifier = Modifier.shadow(4.dp))
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(onClick = {introViewModel.results = true ; addScreen(Boolean)}, modifier = Modifier.background(Color.LightGray)) {
                Text(text = "New Campaign")
            }
            TextButton(onClick = { introViewModel.results = false ; addScreen(Boolean)}, modifier = Modifier.background(Color.LightGray)) {
                Text(text = "New One-Shot")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Display:")
            Box(modifier = Modifier.size(20.dp).background(color = Color.Red).clickable { expanded = true }){
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Campaign") }, onClick = { expanded = false})
                    DropdownMenuItem(text = { Text(text = "One-Shot") }, onClick = { expanded = false})

                }
            }

        }

    }
}

@Preview (showSystemUi = true)
@Composable
fun InitialPreview() {
    IntroScreen{}
}