@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.viewmodels.SelectionViewModel

@Composable
fun SelectionScreen(back: () -> Unit, selectionViewModel: SelectionViewModel = viewModel()) {
    val campaign by selectionViewModel.campaign.observeAsState()
    val oneShot by selectionViewModel.oneShot.observeAsState()

    val label = if(campaign == null){
        oneShot?.shotTitle.toString()
    } else if (oneShot == null){
        campaign?.title.toString()
    } else {
        "Error"
    }


    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = label,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.shadow(4.dp),
            navigationIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            back()
                        })
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectionPreview() {
    DNDToolsTheme {
        SelectionScreen({})
    }
}