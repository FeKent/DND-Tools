package com.example.dndtools.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dndtools.data.Adventure
import com.example.dndtools.data.AdventureType
import com.example.dndtools.ui.theme.DNDToolsTheme
import com.example.dndtools.ui.theme.cambridge
import com.example.dndtools.ui.theme.light1
import com.example.dndtools.viewmodels.IntroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    adventures: List<Adventure>,
    introViewModel: IntroViewModel = viewModel(),
    addScreen: (Any?) -> Unit,
    onShotTap: (Adventure) -> Unit,
    onCampaignTap: (Adventure) -> Unit,
    ) {
    var expanded by remember { mutableStateOf(false) }
    var selectedAdventureType by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Welcome GM!",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .shadow(4.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(
                onClick = { introViewModel.results = true; addScreen(introViewModel.results) },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "New Campaign")
            }
            TextButton(
                onClick = { introViewModel.results = false; addScreen(introViewModel.results) },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "New One-Shot")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { newValue -> expanded = newValue },
                modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
            ) {
                TextField(
                    value = selectedAdventureType ?: "", onValueChange = {}, readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    placeholder = {
                        Text(
                            text = "Select Adventure Type",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }, modifier = Modifier.menuAnchor(), colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(cambridge)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Campaign",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Displaying Campaigns";expanded = false
                        })
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "One-Shot",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Displaying One-Shots"; expanded = false
                        })
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Both",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        onClick = {
                            selectedAdventureType = "Select Adventure Type"; expanded = false
                        })
                }
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "Title",
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.SemiBold,
                color = light1
            )
            Text(
                text = "Players",
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.SemiBold,
                color = light1
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val sortedAdventures: List<Adventure> = adventures.sortedBy { it.title.removePrefix("The") }

            if (selectedAdventureType?.contains("One-Shots") == true) {
                sortedAdventures.forEach { item ->
                    if (item.adventureType == AdventureType.OneShot) {
                        OneShotRow(adventure = item, onShotTap = onShotTap)
                    }
                }
            } else if (selectedAdventureType?.contains("Campaign") == true) {
                sortedAdventures.forEach { item ->
                    if (item.adventureType == AdventureType.Campaign) {
                        CampaignRow(
                            adventure = item, onCampaignTap = onCampaignTap,
                        )
                    }
                }
            } else {
                sortedAdventures.forEach { item ->
                    if (item.adventureType == AdventureType.OneShot) {
                        OneShotRow(adventure = item, onShotTap = onShotTap)
                    } else {
                        CampaignRow(
                            adventure = item, onCampaignTap = onCampaignTap,
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier.padding(horizontal = 32.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun CampaignRow(adventure: Adventure, onCampaignTap: (Adventure) -> Unit) {
    Box(modifier = Modifier
        .padding(horizontal = 32.dp)
        .fillMaxWidth()
        .clickable { onCampaignTap(adventure) }) {
        Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = adventure.title,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(2f),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = adventure.players.toString(),
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(0.5f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Divider(color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun OneShotRow(adventure: Adventure, onShotTap: (Adventure) -> Unit) {
    Box(modifier = Modifier
        .padding(horizontal = 32.dp)
        .fillMaxWidth()
        .clickable { onShotTap(adventure) }
    ) {
        Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = adventure.title,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(2f),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = adventure.players.toString(),
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(0.5f),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Divider(color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showSystemUi = true/*, uiMode = UI_MODE_NIGHT_YES*/)
@Composable
fun InitialPreview() {
    val exampleAdventures = listOf(
        Adventure(
            id = 1,
            adventureType = AdventureType.Campaign,
            title = "Misfits of Fire and Dice",
            players = 2,
            setting = "Sword's Coast"
        ), Adventure(
            id = 1,
            adventureType = AdventureType.Campaign,
            title = "Once and Again",
            players = 4,
            setting = "Verulien"
        ), Adventure(
            id = 1,
            adventureType = AdventureType.OneShot,
            title = "Moon Over Graymoor",
            players = 3,
            setting = "Sword's Coast"
        ), Adventure(
            id = 1,
            adventureType = AdventureType.OneShot,
            title = "Death House",
            players = 6,
            setting = "Barovia"
        )
    )
    DNDToolsTheme {
        IntroScreen(
            adventures = exampleAdventures,
            addScreen = {},
            onCampaignTap = { _ -> },
            onShotTap = { _ -> }
        )
    }
}