package com.example.dndtools.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndtools.data.Campaign

@Composable
fun AddCampaignScreen(onCampaignEntered: (Campaign) -> Unit) {
    var title by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }
    var characters by remember { mutableStateOf("") }
    var setting by remember { mutableStateOf("") }

    Column {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                AddTextField(
                    label = "Campaign Title",
                    value = title,
                    onValueChange = { title = it })
                AddTextField(label = "Players", value = players, onValueChange = { players = it })
                AddTextField(
                    label = "Characters",
                    value = characters,
                    onValueChange = { characters = it })
                AddTextField(label = "Setting", value = setting, onValueChange = { setting = it })
            }
        }
        IconButton(onClick = {
            val newCampaign = Campaign(
                title = title,
                players = players.split(",").toTypedArray(),
                characters = characters.split(",").toTypedArray(),
                setting = setting
            )
            onCampaignEntered(newCampaign)
        }) {
            Icon(Icons.Filled.Add, "Add Campaign")
        }
    }
}

@Composable
fun AddTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value, onValueChange = { onValueChange(it) }, singleLine = true,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words
        ),
        modifier = modifier
    )
}


@Preview
@Composable
fun AddPreview() {
    AddCampaignScreen()
}