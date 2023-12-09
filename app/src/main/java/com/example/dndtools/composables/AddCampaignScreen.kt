package com.example.dndtools.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dndtools.data.Campaign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCampaignScreen(onCampaignEntered: (Campaign) -> Unit) {
    var title by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }
    var characters by remember { mutableStateOf("") }
    var setting by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(title = { Text(text = "Add Campaign")}, modifier = Modifier.shadow(4.dp))
        Spacer(modifier = Modifier.size(32.dp))
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                AddTextField(
                    label = "Campaign Title",
                    value = title,
                    onValueChange = { title = it })
                Spacer(modifier = Modifier.size(8.dp))
                AddTextField(label = "Players", value = players, onValueChange = { players = it })
                Spacer(modifier = Modifier.size(8.dp))
                AddTextField(
                    label = "Characters",
                    value = characters,
                    onValueChange = { characters = it })
                Spacer(modifier = Modifier.size(8.dp))
                AddTextField(label = "Setting", value = setting, onValueChange = { setting = it })
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        IconButton(onClick = {
            val newCampaign = Campaign(
                title = title,
                players = players.split(",").toTypedArray(),
                characters = characters.split(",").toTypedArray(),
                setting = setting
            )
            onCampaignEntered(newCampaign)
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
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


@Preview (showSystemUi = true)
@Composable
fun AddPreview() {
    AddCampaignScreen{}
}