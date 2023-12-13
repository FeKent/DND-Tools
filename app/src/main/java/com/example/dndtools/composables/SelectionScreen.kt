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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.OneShot
import com.example.dndtools.ui.theme.DNDToolsTheme

@Composable
fun SelectionScreen(campaign: Campaign?, oneShot: OneShot?, back: () -> Unit) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = campaign?.title ?: oneShot?.shotTitle ?: "Default Title",
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
        SelectionScreen(
            campaign = Campaign(
                id = 1,
                title = "Misfits of Fire and Dice",
                arrayOf("Fiona", "Cip"),
                arrayOf("Myra", "Pipin"),
                "Sword's Coast"
            ), oneShot = null
        ){}
    }
}