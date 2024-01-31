@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.data.CharacterInfo
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.viewmodels.CharacterViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterInfoScreen(
    adventure: Adventure,
    onInfoEntered: (CharacterInfo) -> Unit,
    back: () -> Unit,
    characterViewModel: CharacterViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "${adventure.title}: Character Info",
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .shadow(4.dp)
                .background(color = MaterialTheme.colorScheme.primary),
            navigationIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            back()
                        }
                )
            }
        )
        Spacer(modifier = Modifier.size(54.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (i in 1..adventure.players) {
                CharacterName(
                    playerNumber = i,
                    totalPlayers = adventure.players,
                    characterViewModel = characterViewModel
                )
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        IconButton(
            onClick = {
                val newCharacterInfo =
                    CharacterInfo(
                        id = adventure.id.absoluteValue,
                        characterNames = characterViewModel.characterNames
                    )
                onInfoEntered.invoke(newCharacterInfo)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                Icons.Filled.Add,
                "Save",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

@Composable
fun CharacterName(playerNumber: Int, totalPlayers: Int, characterViewModel: CharacterViewModel) {
    var characterName by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isLastPlayer = playerNumber == totalPlayers

    Row {
        TextField(
            value = characterName,
            onValueChange = { characterName = it },
            singleLine = true,
            label = {
                Text(
                    text = "Character $playerNumber",
                    color = MaterialTheme.colorScheme.primary
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = if (isLastPlayer) ImeAction.Done else ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                characterViewModel.addCharacterName(characterName)
                focusManager.moveFocus((FocusDirection.Next))
            }, onDone = {
                characterViewModel.addCharacterName(characterName)
                keyboardController?.hide()
            }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
        )
    }
    Spacer(modifier = Modifier.size(4.dp))
}


@Preview(showSystemUi = true)
@Composable
fun PlayerPreview() {
    DNDToolsTheme {
        CharacterInfoScreen(
            adventure = Adventure(
                1,
                AdventureType.OneShot,
                "Misfits",
                3,
                "Sword's Coast"
            ), {}, {})
    }
}