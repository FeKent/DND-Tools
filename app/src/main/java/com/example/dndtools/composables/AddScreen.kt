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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Campaign
import com.example.dndtools.data.OneShot
import com.example.dndtools.viewmodels.AddViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    addViewModel: AddViewModel = viewModel(),
    onCampaignEntered: ((Campaign) -> Unit)? = null,
    onOneShotEntered: ((OneShot) -> Unit)? = null
) {
    val results = addViewModel.results

    var title by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }
    var characters by remember { mutableStateOf("") }
    var setting by remember { mutableStateOf("") }

    var shotTitle by remember { mutableStateOf("") }
    var shotPlayers by remember { mutableStateOf("") }
    var shotSetting by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { if(results) {Text(text = "Add Campaign")} else {Text(text = "Add One-Shot")} },
            modifier = Modifier.shadow(4.dp)
        )
        Spacer(modifier = Modifier.size(32.dp))
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (results) {
                    AddTextField(
                        label = "Campaign Title",
                        value = title,
                        onValueChange = { title = it })
                    Spacer(modifier = Modifier.size(8.dp))
                    AddTextField(
                        label = "Players",
                        value = players,
                        onValueChange = { players = it })
                    Spacer(modifier = Modifier.size(8.dp))
                    AddTextField(
                        label = "Characters",
                        value = characters,
                        onValueChange = { characters = it })
                    Spacer(modifier = Modifier.size(8.dp))
                    AddTextField(
                        label = "Setting",
                        value = setting,
                        onValueChange = { setting = it })
                } else {
                    AddTextField(
                        label = "One-Shot Title",
                        value = shotTitle,
                        onValueChange = { shotTitle = it })
                    Spacer(modifier = Modifier.size(8.dp))
                    AddNumField(
                        label = "Players",
                        value = shotPlayers,
                        onValueChange = { shotPlayers = it })
                    Spacer(modifier = Modifier.size(8.dp))
                    AddTextField(
                        label = "Setting",
                        value = shotSetting,
                        onValueChange = { shotSetting = it })
                }


            }
        }
        Spacer(modifier = Modifier.size(8.dp))

        if (results) {
            IconButton(onClick = {
                val newCampaign = Campaign(
                    title = title,
                    players = players.split(",").toTypedArray(),
                    characters = characters.split(",").toTypedArray(),
                    setting = setting
                )
                onCampaignEntered?.invoke(newCampaign)
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(Icons.Filled.Add, "Add Campaign")
            }
        } else {
            IconButton(onClick = {
                val newOneShot = OneShot(
                    shotTitle = shotTitle,
                    shotPlayers = shotPlayers.toInt(),
                    shotSetting = shotSetting
                )
                onOneShotEntered?.invoke(newOneShot)
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(Icons.Filled.Add, "Add One-Shot")
            }
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

@Composable
fun AddNumField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value, onValueChange = { onValueChange(it) }, singleLine = true,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}


//@Preview(showSystemUi = true)
//@Composable
//fun AddPreview() {
//    AddScreen{}
//}