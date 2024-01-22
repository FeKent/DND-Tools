@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.ui.theme.DNDToolsTheme

@Composable
fun EditScreen(adventure: Adventure, back: () -> Unit) {

    var title by remember { mutableStateOf(adventure.title) }
    var adventureType by remember { mutableStateOf(adventure.adventureType.name) }
    var players by remember { mutableStateOf(adventure.players.toString()) }
    var setting by remember { mutableStateOf(adventure.setting) }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Edit ${adventure.adventureType.name}: ${adventure.title}",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.ExtraBold
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
        Spacer(modifier = Modifier.size(32.dp))
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                AddTextField(
                    label = "${adventure.adventureType.name} Title",
                    value = title,
                    onValueChange = { title = it })
                Spacer(modifier = Modifier.size(8.dp))
                AddNumField(
                    label = "Players",
                    value = players,
                    onValueChange = { players = it })
                Spacer(modifier = Modifier.size(8.dp))
                AddTextField(
                    label = "Setting",
                    value = setting,
                    onValueChange = { setting = it })
                Spacer(modifier = Modifier.size(8.dp))
                ExposedDropdownMenuBox(expanded, onExpandedChange = { expanded = false }) {
                    TextField(
                        value = adventure.adventureType.name,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text(
                                text = "Adventure Type", color = MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingIcon = { TrailingIcon(expanded) },
                        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        )

                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun EditPreview() {
    DNDToolsTheme {
        EditScreen(
            adventure = Adventure(
                1,
                AdventureType.OneShot,
                "Misfits",
                3,
                "Sword's Coast"
            )
        ) {}
    }
}