package com.example.dndtools.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialSetUp() {
    Column {
        CenterAlignedTopAppBar(title = { Text(text = "Welcome GM!")})

    }
}

@Preview (showSystemUi = true)
@Composable
fun InitialPreview() {
    InitialSetUp()
}