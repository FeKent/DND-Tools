package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.viewmodels.InitiativeViewModel

enum class ScreenState {
    Input,
    Output
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitiativeScreen(
    back: () -> Unit,
    adventure: Adventure?,
    initiativeViewModel: InitiativeViewModel = viewModel()
) {
    var currentState by remember { mutableStateOf(ScreenState.Input) }
    val state = initiativeViewModel.enemies
    var enemies by remember { mutableStateOf(state?.toString() ?: "") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Initiative Tracker",
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.primary),
            navigationIcon = {
                Icon(Icons.Filled.KeyboardArrowLeft,
                    "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            back()
                        })
            }
        )
        Spacer(modifier = Modifier.size(54.dp))
        when (currentState) {
            ScreenState.Input -> {
                Box {
                    Column {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AddNumField(
                                label = "Number of Enemies",
                                value = enemies,
                                onValueChange = { enemies = it; enemies.toInt() })
                        }
                        Spacer(modifier = Modifier.size(54.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "Character Rolls", fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            for (i in 1..(adventure?.players ?: 0)) {
                                PlayerRoll(players = i, initiativeViewModel)
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                            Spacer(modifier = Modifier.size(54.dp))
                            TextButton(
                                onClick = {
                                    currentState = ScreenState.Output
                                    initiativeViewModel.setEnemiesCount(enemies.toInt())
                                    initiativeViewModel.generateInitiativeRolls()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onBackground
                                ),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                Text(text = "Next", fontSize = 35.sp)
                            }
                        }

                    }
                }
            }

            ScreenState.Output -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Round: 1",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.size(16.dp))

                    val allRolls =
                        (initiativeViewModel.characterRolls + initiativeViewModel.enemyInitiativeRolls)
                            .withIndex()
                            .sortedByDescending { it.value }

                    allRolls.forEach { (index, roll) ->
                        val rollType = if (index < initiativeViewModel.characterRolls.size) {
                            "Character ${index + 1}"
                        } else {
                            "Enemy ${index + 1 - initiativeViewModel.characterRolls.size}"
                        }

                        Row {
                            Text(
                                text = "$rollType: ",
                                color = if (rollType.contains("Character")) {
                                    MaterialTheme.colorScheme.onBackground
                                } else {
                                    MaterialTheme.colorScheme.secondary
                                },
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = "$roll",
                                color = if (rollType.contains("Character")) {
                                    MaterialTheme.colorScheme.secondary
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PlayerRoll(players: Int, initiativeViewModel: InitiativeViewModel) {
    var playerRoll by remember { mutableStateOf("") }
    Row {
        TextField(
            value = playerRoll,
            onValueChange = { playerRoll = it },
            singleLine = true,
            label = { Text(text = "Character: $players", color = MaterialTheme.colorScheme.primary)  },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onNext = {initiativeViewModel.addCharacterRoll(playerRoll.toInt()) ; KeyboardActions.Default}),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
        )
    }
}

@Preview
@Composable
fun InitiativePreview() {
    DNDToolsTheme {
        val viewModel = InitiativeViewModel().apply {
            enemies = 4
            generateInitiativeRolls() // Optional: Generate initiative rolls for the preview
        }
        InitiativeScreen(
            adventure = Adventure(
                1,
                AdventureType.OneShot,
                "Moon Over Graymoor",
                4,
                "Sword's Coast"
            ), back = {}, initiativeViewModel = viewModel
        )
    }
}